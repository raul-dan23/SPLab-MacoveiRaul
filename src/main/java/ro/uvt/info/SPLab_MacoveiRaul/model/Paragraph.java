package ro.uvt.info.SPLab_MacoveiRaul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Paragraph extends Element {
    private String text;
    // strategie de randare (interfata, fara mapare JDBC) -> nepersistata
    @Transient
    private AlignStrategy alignStrategy;

    protected Paragraph() {
    }

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
    }

    public void render() {
        if (alignStrategy != null) {
            alignStrategy.render(this, new Context());
        } else {
            System.out.println("Paragraph: " + text);
        }
    }

    @Override
    public void add(Element e) {
        throw new UnsupportedOperationException("Paragraph is a leaf and cannot have children");
    }

    @Override
    public void remove(Element e) {
        throw new UnsupportedOperationException("Paragraph is a leaf and cannot have children");
    }

    @Override
    public Element get(int i) {
        throw new UnsupportedOperationException("Paragraph is a leaf and cannot have children");
    }

    @Override
    public String toString() {
        return "Paragraph \"" + text + "\"";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
