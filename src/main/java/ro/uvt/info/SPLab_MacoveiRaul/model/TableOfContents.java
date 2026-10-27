package ro.uvt.info.SPLab_MacoveiRaul.model;

import java.util.ArrayList;
import java.util.List;

public class TableOfContents extends Element {
    private List<String> entries = new ArrayList<>();

    public TableOfContents() {
    }

    public void addEntry(String entry) {
        entries.add(entry);
    }

    public List<String> getEntries() {
        return entries;
    }

    public void render() {
        System.out.println("Table of contents");
        for (String entry : entries) {
            System.out.println(entry);
        }
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

    @Override
    public String toString() {
        return "TableOfContents";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
