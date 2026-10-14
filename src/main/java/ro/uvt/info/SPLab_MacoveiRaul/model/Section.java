package ro.uvt.info.SPLab_MacoveiRaul.model;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
    protected String title;
    protected List<Element> children = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
        printHeader();
        for (Element child : children) {
            child.print();
        }
    }

    protected void printHeader() {
        System.out.println(title);
    }

    @Override
    public void add(Element e) {
        children.add(e);
    }

    @Override
    public void remove(Element e) {
        children.remove(e);
    }

    @Override
    public Element get(int i) {
        return children.get(i);
    }
}
