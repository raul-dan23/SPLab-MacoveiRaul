package ro.uvt.info.SPLab_MacoveiRaul.model;

public class ImageProxy implements Element {
    private String url;
    private Image realImg = null;

    public ImageProxy(String url) {
        // doar retine url-ul; Image-ul real se creeaza abia la primul print()
        this.url = url;
    }

    private Image loadImage() {
        if (realImg == null) {
            realImg = new Image(url);
        }
        return realImg;
    }

    @Override
    public void print() {
        loadImage().print();
    }

    @Override
    public void add(Element e) {
        throw new UnsupportedOperationException("ImageProxy is a leaf and cannot have children");
    }

    @Override
    public void remove(Element e) {
        throw new UnsupportedOperationException("ImageProxy is a leaf and cannot have children");
    }

    @Override
    public Element get(int i) {
        throw new UnsupportedOperationException("ImageProxy is a leaf and cannot have children");
    }
}
