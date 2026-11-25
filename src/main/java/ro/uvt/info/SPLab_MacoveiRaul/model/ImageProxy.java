package ro.uvt.info.SPLab_MacoveiRaul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class ImageProxy extends Element {
    private String url;
    // cache runtime al imaginii incarcate lazy; nu se persista, se reconstruieste din url
    @Transient
    private Image realImg = null;

    protected ImageProxy() {
    }

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
    public String toString() {
        return "ImageProxy \"" + url + "\"";
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
