package ro.uvt.info.SPLab_MacoveiRaul.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
public class Section extends Element {
    protected String title;
    // partea "one": FK-ul sta in coloana parent_id a copilului (mappedBy), fara tabel de legatura
    // @OrderBy: fara el, ordinea copiilor la reincarcare nu e garantata
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    protected List<Element> children = new ArrayList<>();

    // constructor fara argumente, cerut de JPA
    protected Section() {
    }

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
