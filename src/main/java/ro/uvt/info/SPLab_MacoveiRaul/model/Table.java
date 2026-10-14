package ro.uvt.info.SPLab_MacoveiRaul.model;

public class Table implements Element {
    private String title;

    public Table(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
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
}
