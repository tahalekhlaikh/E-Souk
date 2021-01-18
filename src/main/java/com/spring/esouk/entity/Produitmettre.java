package com.spring.esouk.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="produitmettre")
public class Produitmettre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduitMettre;
    @NotNull
    private  String description;
    @NotNull
    private String nom;
    @NotNull
    private int qte;
    @NotNull
    private int prix;
    @OneToOne
    @JoinColumn(name="idProduit")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name="idAgriculteur")
    private Agriculteur agriculteur;

        private int nombreVendu;

    public int getNombreVendu() {
        return nombreVendu;
    }

    public void setNombreVendu(int nombreVendu) {
        this.nombreVendu = nombreVendu;
    }

    public Produitmettre(String description, String nom, int qte, int prix, Produit produit, Agriculteur agriculteur) {
        this.description = description;
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
        this.produit = produit;
        this.agriculteur = agriculteur;
    }

    public int getIdProduitMettre() {
        return idProduitMettre;
    }

    public void setIdProduitMettre(int idProduitMettre) {
        this.idProduitMettre = idProduitMettre;
    }

    public Agriculteur getAgriculteur() {
        return agriculteur;
    }

    public void setAgriculteur(Agriculteur agriculteur) {
        this.agriculteur = agriculteur;
    }

    public int getId() {
        return idProduitMettre;
    }

    public void setId(int id) {
        this.idProduitMettre = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Produitmettre() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Produitmettre{" +
                "id=" + idProduitMettre +
                ", description='" + description + '\'' +
                ", nom='" + nom + '\'' +
                ", qte=" + qte +
                ", prix=" + prix +
                ", produit=" + produit +
                '}';
    }

}
