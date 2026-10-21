package ro.uvt.info.SPLab_MacoveiRaul.model;

import java.util.concurrent.TimeUnit;

public class Image implements Element {
    private String url;

    public Image(String url) {
        this.url = url;
        // simuleaza o incarcare costisitoare (ex. descarcare de pe disc/retea)
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getUrl() {
        return url;
    }

    public void render() {
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

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
