package ro.uvt.info.SPLab_MacoveiRaul.model;

public class Context {
    public static final int DEFAULT_LINE_WIDTH = 40;

    private int lineWidth;
    private int indent;

    public Context() {
        this(DEFAULT_LINE_WIDTH, 0);
    }

    public Context(int lineWidth, int indent) {
        this.lineWidth = lineWidth;
        this.indent = indent;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public int getIndent() {
        return indent;
    }
}
