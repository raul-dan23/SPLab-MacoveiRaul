package ro.uvt.info.SPLab_MacoveiRaul.model;

public class Paragraph implements Element {
    private String text;
    private AlignStrategy alignStrategy;

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
    public void accept(Visitor v) {
        v.visit(this);
    }
}
