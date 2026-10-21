package ro.uvt.info.SPLab_MacoveiRaul.model;

public class RenderContentVisitor implements Visitor {

    @Override
    public void visit(Book b) {
        b.render();
    }

    @Override
    public void visit(Section section) {
        section.render();
    }

    @Override
    public void visit(TableOfContents table) {
        table.render();
    }

    @Override
    public void visit(Paragraph paragraph) {
        paragraph.render();
    }

    @Override
    public void visit(Image image) {
        image.render();
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        imageProxy.render();
    }

    @Override
    public void visit(Table table) {
        table.render();
    }
}
