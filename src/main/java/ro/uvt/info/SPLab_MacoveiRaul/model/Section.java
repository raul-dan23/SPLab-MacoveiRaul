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

    public void render() {
        System.out.println(title);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        acceptChildren(v);
    }

    protected void acceptChildren(Visitor v) {
        for (Element child : children) {
            child.accept(v);
        }
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
