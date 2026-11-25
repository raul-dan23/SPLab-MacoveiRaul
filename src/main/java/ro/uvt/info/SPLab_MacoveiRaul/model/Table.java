package ro.uvt.info.SPLab_MacoveiRaul.model;

import jakarta.persistence.Entity;

// numele explicit: "table" e cuvant rezervat in SQL, iar JOINED creeaza un tabel per clasa
@Entity
@jakarta.persistence.Table(name = "table_element")
public class Table extends Element {
    private String title;

    protected Table() {
    }

    public Table(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void render() {
        System.out.println("Table with title: " + title);
    }

    @Override
    public void add(Element e) {
        throw new UnsupportedOperationException("Table is a leaf and cannot have children");
    }

    @Override
    public void remove(Element e) {
        throw new UnsupportedOperationException("Table is a leaf and cannot have children");
    }

    @Override
    public Element get(int i) {
        throw new UnsupportedOperationException("Table is a leaf and cannot have children");
    }

    @Override
    public String toString() {
        return "Table \"" + title + "\"";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
