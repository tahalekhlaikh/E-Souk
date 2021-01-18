package com.spring.esouk.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="boutique")
public class Boutique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBoutique;
    private String nom;
    private java.util.Date date;
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return idBoutique;
    }
    public void setId(int id) {
        this.idBoutique = id;
    }

    public int getIdBoutique() {
        return idBoutique;
    }

    public void setIdBoutique(int idBoutique) {
        this.idBoutique = idBoutique;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }


    public Boutique() {
    }

    public Boutique(String nom, java.util.Date date, Agriculteur agr) {
        this.nom = nom;
        this.date = date;
    }
}
