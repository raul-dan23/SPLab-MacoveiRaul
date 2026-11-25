package ro.uvt.info.SPLab_MacoveiRaul.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// radacina ierarhiei Composite; clasa abstracta poate fi @Entity (nu se instantiaza direct)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // sectiunea care contine elementul; null cat timp elementul nu e adaugat nicaieri
    // partea "many" a relatiei Section.children (coloana parent_id); Hibernate o seteaza direct pe camp, fara setParent
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Section parent = null;

    public Integer getId() {
        return id;
    }

    // @JsonIgnore: altfel parent <-> children se serializeaza la infinit
    @JsonIgnore
    public Section getParent() {
        return parent;
    }

    // package-private: doar Section (din acelasi pachet) poate schimba parintele
    void setParent(Section parent) {
        this.parent = parent;
    }

    public abstract void add(Element e);

    public abstract void remove(Element e);

    public abstract Element get(int i);

    public abstract void accept(Visitor v);
}
