package ro.uvt.info.SPLab_MacoveiRaul.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;

@Entity
public class Book extends Section {
    // un autor poate scrie mai multe carti si o carte poate avea mai multi autori -> tabel de legatura
    // PERSIST/MERGE (nu ALL): stergerea unei carti nu trebuie sa stearga autorii, care pot fi partajati
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Author> authors = new ArrayList<>();

    protected Book() {
    }

    public Book(String title) {
        super(title);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addContent(Element element) {
        add(element);
    }

    @Override
    public void render() {
        System.out.println("Book: " + title);
        System.out.println("Authors:");
        for (Author author : authors) {
            author.render();
        }
    }

    @Override
    public String toString() {
        return "Book \"" + title + "\"";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        acceptChildren(v);
    }
}
