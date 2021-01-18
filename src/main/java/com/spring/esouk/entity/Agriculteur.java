package com.spring.esouk.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="agriculteur")
public class Agriculteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAgriculteur;

    private String nom;
    private String prenom;
    private String type;
    private String adresse;
    private String photo;
    private int etoile;
    private double etoileF;
    @OneToOne
    @JoinColumn(name="boutique")
    private Boutique boutique;
    private String cin;
    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToMany
    private Collection<Commande> commandes=new ArrayList<>();

    @OneToMany
    private List<Produitmettre> produitmettres=new ArrayList<>();

    public int getIdAgriculteur() {
        return idAgriculteur;
    }

    public void setIdAgriculteur(int idAgriculteur) {
        this.idAgriculteur = idAgriculteur;
    }

    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return idAgriculteur;
    }

    public void setId(int id) {
        this.idAgriculteur = id;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public Agriculteur() {
    }

    public Agriculteur(String nom, String prenom, String type, String adresse, String photo, Boutique boutique, String cin, User user) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.adresse = adresse;
        this.photo = photo;
        this.boutique = boutique;
        this.cin = cin;
        this.user = user;
    }
    public void evaluer(double etoile)
    {
        setEtoileF(this.etoileF+(etoile/100));

        setEtoile((int)this.etoileF);

    }

    public double getEtoileF() {
        return etoileF;
    }

    public void setEtoileF(double etoileF) {
        this.etoileF = etoileF;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<Produitmettre> getProduitmettres() {
        return produitmettres;
    }

    public void setProduitmettres(List<Produitmettre> produitmettres) {
        this.produitmettres = produitmettres;
    }
}
