package ro.uvt.info.SPLab_MacoveiRaul.model;

public class Image implements Element {
    private String url;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void print() {
        System.out.println("Image with name:" + url);
    }

    @Override
    public void add(Element e) {
        throw new UnsupportedOperationException("Image is a leaf and cannot have children");
    }

    @Override
    public void remove(Element e) {
        throw new UnsupportedOperationException("Image is a leaf and cannot have children");
    }

    @Override
    public Element get(int i) {
        throw new UnsupportedOperationException("Image is a leaf and cannot have children");
    }
}
