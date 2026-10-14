package ro.uvt.info.SPLab_MacoveiRaul.model;

public class AlignRight implements AlignStrategy {

    @Override
    public void render(Paragraph p, Context ctx) {
        String text = p.getText();
        int padding = Math.max(0, ctx.getLineWidth() - text.length());
        System.out.println(" ".repeat(ctx.getIndent()) + "|" + " ".repeat(padding) + text + "|");
    }
}
