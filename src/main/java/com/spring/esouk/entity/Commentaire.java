package com.spring.esouk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
@Entity
public class Commentaire {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateDeCreation;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Agriculteur agr;

    private String commentaire;

    @ManyToOne
    private Produitmettre produitmettre;

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", dateDeCreation=" + dateDeCreation +
                ", client=" + client +
                ", agr=" + agr +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }

    public Commentaire() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agriculteur getAgr() {
        return agr;
    }

    public void setAgr(Agriculteur agr) {
        this.agr = agr;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Produitmettre getProduitmettre() {
        return produitmettre;
    }

    public void setProduitmettre(Produitmettre produitmettre) {
        this.produitmettre = produitmettre;
    }
}
