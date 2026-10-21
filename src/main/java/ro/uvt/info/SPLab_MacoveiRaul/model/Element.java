package ro.uvt.info.SPLab_MacoveiRaul.model;

public interface Element {
    void add(Element e);

    void remove(Element e);

    Element get(int i);

    void accept(Visitor v);
}
