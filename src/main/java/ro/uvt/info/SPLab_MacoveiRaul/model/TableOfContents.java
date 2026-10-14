package ro.uvt.info.SPLab_MacoveiRaul.model;

public class TableOfContents implements Element {

    public TableOfContents() {
    }

    @Override
    public void print() {
        System.out.println("Table of contents");
    }

    @Override
    public void add(Element e) {
        throw new UnsupportedOperationException("TableOfContents is a leaf and cannot have children");
    }

    @Override
    public void remove(Element e) {
        throw new UnsupportedOperationException("TableOfContents is a leaf and cannot have children");
    }

    @Override
    public Element get(int i) {
        throw new UnsupportedOperationException("TableOfContents is a leaf and cannot have children");
    }
}
