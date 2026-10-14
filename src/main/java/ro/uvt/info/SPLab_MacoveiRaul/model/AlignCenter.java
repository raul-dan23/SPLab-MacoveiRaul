package ro.uvt.info.SPLab_MacoveiRaul.model;

public class AlignCenter implements AlignStrategy {

    @Override
    public void render(Paragraph p, Context ctx) {
        String text = p.getText();
        int padding = Math.max(0, ctx.getLineWidth() - text.length());
        int left = padding / 2;
        int right = padding - left;
        System.out.println(" ".repeat(ctx.getIndent()) + "|" + " ".repeat(left) + text + " ".repeat(right) + "|");
    }
}
