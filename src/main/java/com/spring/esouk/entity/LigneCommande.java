package com.spring.esouk.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class LigneCommande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idLigneCommande;
    @OneToOne
    @JoinColumn(name="idProduitMettre")
    private Produitmettre produitmettre;
    private int quantite;
    private double prix;
    private int approuve;

    public int getId() {
        return idLigneCommande;
    }

    public void setId(int id) {
        this.idLigneCommande = id;
    }

    public Produitmettre getProduitmettre() {
        return produitmettre;
    }

    public void setProduitmettre(Produitmettre produitmettre) {
        this.produitmettre = produitmettre;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LigneCommande(Produitmettre produitmettre, int quantite, double prix) {
        this.produitmettre = produitmettre;
        this.quantite = quantite;
        this.prix = prix;
    }

    public LigneCommande() {
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "idLigneCommande=" + idLigneCommande +
                ", produitmettre=" + produitmettre +
                ", quantite=" + quantite +
                ", prix=" + prix +
                '}';
    }

    public int getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(int idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public int getApprouve() {
        return approuve;
    }

    public void setApprouve(int approuve) {
        this.approuve = approuve;
    }
}
