package ro.uvt.info.SPLab_MacoveiRaul.model;

import java.util.ArrayList;
import java.util.List;

public class Book extends Section {
    private List<Author> authors = new ArrayList<>();

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
