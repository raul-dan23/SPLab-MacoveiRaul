package ro.uvt.info.SPLab_MacoveiRaul.model;

public class BookStatistics implements Visitor {
    private int paragraphCount = 0;
    private int imageCount = 0;
    private int tableCount = 0;

    @Override
    public void visit(Book b) {
    }

    @Override
    public void visit(Section section) {
    }

    @Override
    public void visit(TableOfContents table) {
    }

    @Override
    public void visit(Paragraph paragraph) {
        paragraphCount++;
    }

    @Override
    public void visit(Image image) {
        imageCount++;
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        // doar numaram proxy-ul, fara sa incarcam imaginea reala
        imageCount++;
    }

    @Override
    public void visit(Table table) {
        tableCount++;
    }

    public void printStatistics() {
        System.out.println("Book Statistics: ");
        System.out.println("*** Number of images: " + imageCount);
        System.out.println("*** Number of tables: " + tableCount);
        System.out.println("*** Number of paragraphs: " + paragraphCount);
    }
}
