package ro.uvt.info.SPLab_MacoveiRaul;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;
import ro.uvt.info.SPLab_MacoveiRaul.service.BooksService;

@SpringBootTest
class BooksPersistenceTests {

    @Autowired
    BooksService service;

    @Test
    void crudCycleIsPersisted() {
        BookDto created = service.create(new BookDto(999L, "Clean Code", "Robert Martin", "123"));
        assertTrue(created.id() != null && created.id() != 999L, "id-ul trebuie generat de baza de date");
        assertEquals("Clean Code", service.findById(created.id()).orElseThrow().title());

        BookDto updated = service.update(created.id(), new BookDto(null, "Clean Coder", "Robert Martin", "456")).orElseThrow();
        assertEquals(created.id(), updated.id());
        assertEquals("Clean Coder", service.findById(created.id()).orElseThrow().title());
        assertTrue(service.findAll().stream().anyMatch(b -> b.id().equals(created.id())));

        assertTrue(service.delete(created.id()));
        assertFalse(service.delete(created.id()));
        assertTrue(service.findById(created.id()).isEmpty());
        assertTrue(service.update(created.id(), updated).isEmpty());
    }
}
