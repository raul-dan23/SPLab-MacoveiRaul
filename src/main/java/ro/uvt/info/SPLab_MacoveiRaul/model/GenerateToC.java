package ro.uvt.info.SPLab_MacoveiRaul.model;

import java.util.IdentityHashMap;
import java.util.Map;

public class GenerateToC implements Visitor {
    private TableOfContents toc = new TableOfContents();
    // adancimea fiecarei sectiuni, inregistrata de parinte inainte ca sectiunea sa fie vizitata
    private Map<Section, Integer> depths = new IdentityHashMap<>();

    @Override
    public void visit(Book b) {
        // titlul cartii nu intra in cuprins; capitolele de pe primul nivel au adancimea 0
        registerChildren(b, 0);
    }

    @Override
    public void visit(Section section) {
        int depth = depths.getOrDefault(section, 0);
        toc.addEntry("  ".repeat(depth) + section.getTitle());
        registerChildren(section, depth + 1);
    }

    // parcurgerea e depth-first si parintele e vizitat inaintea copiilor,
    // deci putem fixa adancimea copiilor-sectiune inainte sa ajungem la ei
    private void registerChildren(Section parent, int childDepth) {
        for (Element child : parent.children) {
            if (child instanceof Section) {
                depths.put((Section) child, childDepth);
            }
        }
    }

    @Override
    public void visit(TableOfContents table) {
    }

    @Override
    public void visit(Paragraph paragraph) {
    }

    @Override
    public void visit(Image image) {
    }

    @Override
    public void visit(ImageProxy imageProxy) {
    }

    @Override
    public void visit(Table table) {
    }

    public TableOfContents getToC() {
        return toc;
    }
}
