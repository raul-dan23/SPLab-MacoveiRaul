package ro.uvt.info.SPLab_MacoveiRaul.model;

public interface Visitor {
    void visit(Book b);
    void visit(Section section);
    void visit(TableOfContents table);
    void visit(Paragraph paragraph);
    void visit(Image image);
    void visit(ImageProxy imageProxy);
    void visit(Table table);
}
