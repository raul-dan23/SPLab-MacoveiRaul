package ro.uvt.info.SPLab_MacoveiRaul.model;

import java.util.ArrayList;
import java.util.List;

public class Section extends Element {
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
        // interzicem partajarea: un element poate apartine unei singure sectiuni
        if (e.getParent() != null) {
            throw new IllegalStateException(
                    e + " already belongs to " + e.getParent() + "; remove it from there before adding it to " + this);
        }
        children.add(e);
        e.setParent(this);
    }

    @Override
    public void remove(Element e) {
        // eliberam elementul doar daca era chiar copilul acestei sectiuni
        if (children.remove(e)) {
            e.setParent(null);
        }
    }

    @Override
    public String toString() {
        return "Section \"" + title + "\"";
    }

    @Override
    public Element get(int i) {
        return children.get(i);
    }
}
