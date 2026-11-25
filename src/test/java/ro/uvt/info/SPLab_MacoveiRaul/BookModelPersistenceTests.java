package ro.uvt.info.SPLab_MacoveiRaul;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import tools.jackson.databind.json.JsonMapper;
import ro.uvt.info.SPLab_MacoveiRaul.model.Author;
import ro.uvt.info.SPLab_MacoveiRaul.model.Book;
import ro.uvt.info.SPLab_MacoveiRaul.model.Element;
import ro.uvt.info.SPLab_MacoveiRaul.model.Image;
import ro.uvt.info.SPLab_MacoveiRaul.model.ImageProxy;
import ro.uvt.info.SPLab_MacoveiRaul.model.Paragraph;
import ro.uvt.info.SPLab_MacoveiRaul.model.Section;
import ro.uvt.info.SPLab_MacoveiRaul.model.Table;
import ro.uvt.info.SPLab_MacoveiRaul.persistence.AuthorsRepository;
import ro.uvt.info.SPLab_MacoveiRaul.persistence.BooksRepository;

@SpringBootTest
@Transactional
class BookModelPersistenceTests {

    @Autowired
    BooksRepository books;
    @Autowired
    AuthorsRepository authors;
    @Autowired
    EntityManager em;
    @Autowired
    JsonMapper json;

    private Book buildBook() {
        Book book = new Book("Noapte buna, copii!");
        book.addAuthor(new Author("Radu Pavel", "Gheo"));
        Section cap1 = new Section("Capitolul 1");
        book.addContent(new Paragraph("Multumesc celor care ..."));
        book.addContent(cap1);
        cap1.add(new Paragraph("Moto capitol"));
        cap1.add(new ImageProxy("poza.png"));
        cap1.add(new Table("Tabel 1"));
        cap1.add(new Image("imagine.png"));
        return book;
    }

    @Test
    void savesAndReloadsWholeHierarchy() {
        Integer id = books.save(buildBook()).getId();
        em.flush();
        em.clear(); // fortam reincarcarea din baza de date

        long start = System.currentTimeMillis();
        Book loaded = books.findById(id).orElseThrow();
        Section cap1 = (Section) loaded.get(1);
        long ms = System.currentTimeMillis() - start;
        System.out.println("LOAD took " + ms + " ms");

        assertEquals("Noapte buna, copii!", loaded.getTitle());
        assertNull(loaded.getParent());
        assertSame(loaded, cap1.getParent(), "parent trebuie repopulat de Hibernate, fara add()");
        assertEquals("Capitolul 1", cap1.getTitle());
        assertTrue(cap1.get(0) instanceof Paragraph);
        assertTrue(cap1.get(1) instanceof ImageProxy);
        assertTrue(cap1.get(2) instanceof Table);
        assertTrue(cap1.get(3) instanceof Image);
        for (int i = 0; i < 4; i++) {
            Element child = cap1.get(i);
            assertSame(cap1, child.getParent());
        }
        assertTrue(ms < 4000, "incarcarea nu trebuie sa treaca prin sleep-ul de 5s din Image(String)");
        assertNotNull(authors.findAll());
        assertEquals(1, authors.count());
    }

    @Test
    void reloadedElementCanStillBeMovedAfterRemove() {
        Integer id = books.save(buildBook()).getId();
        em.flush();
        em.clear();
        Book loaded = books.findById(id).orElseThrow();
        Section cap1 = (Section) loaded.get(1);
        Element p = cap1.get(0);
        cap1.remove(p);
        assertNull(p.getParent());
        loaded.add(p); // add() functioneaza normal pe obiecte incarcate
        assertSame(loaded, p.getParent());
    }

    @Test
    void reloadedHierarchySerializesWithoutInfiniteRecursion() throws Exception {
        Integer id = books.save(buildBook()).getId();
        em.flush();
        em.clear();
        Book loaded = books.findById(id).orElseThrow();
        Section cap1 = (Section) loaded.get(1);
        // parent <-> children ar recursa la infinit fara @JsonIgnore pe getParent()
        String bookJson = json.writeValueAsString(loaded);
        String sectionJson = json.writeValueAsString(cap1);
        System.out.println("JSON book=" + bookJson);
        System.out.println("JSON section=" + sectionJson);
        assertTrue(bookJson.contains("Noapte buna"));
        assertTrue(!sectionJson.contains("parent"));
    }
}
