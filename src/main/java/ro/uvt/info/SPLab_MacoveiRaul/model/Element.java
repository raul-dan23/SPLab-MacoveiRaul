package ro.uvt.info.SPLab_MacoveiRaul.model;

public abstract class Element {
    // sectiunea care contine elementul; null cat timp elementul nu e adaugat nicaieri
    private Section parent = null;

    public Section getParent() {
        return parent;
    }

    // package-private: doar Section (din acelasi pachet) poate schimba parintele
    void setParent(Section parent) {
        this.parent = parent;
    }

    public abstract void add(Element e);

    public abstract void remove(Element e);

    public abstract Element get(int i);

    public abstract void accept(Visitor v);
}
