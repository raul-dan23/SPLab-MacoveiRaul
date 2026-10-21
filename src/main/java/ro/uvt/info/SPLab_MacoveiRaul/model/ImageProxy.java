package ro.uvt.info.SPLab_MacoveiRaul.model;

public class ImageProxy implements Element {
    private String url;
    private Image realImg = null;

    public ImageProxy(String url) {
        // doar retine url-ul; Image-ul real se creeaza abia cand e ceruta imaginea propriu-zisa
        this.url = url;
    }

    private Image loadImage() {
        if (realImg == null) {
            realImg = new Image(url);
        }
        return realImg;
    }

    public void render() {
        // la randare chiar avem nevoie de imagine: o incarcam (o singura data) si delegam
        loadImage().render();
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

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
