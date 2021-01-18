package com.spring.esouk.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Client  implements Serializable {

    @Id
    @GeneratedValue
    private int idClient;

    private String nom;
    private String prenom;
    private String photo;
    private String adresse;
    private int telephone;
    @OneToMany(mappedBy="client")
    private Collection<Commande> commandes;
    @OneToOne
    private User user;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    public Client(String nom, String prenom, String photo, String adresse, Collection<Commande> commandes,int telephone,User user) {
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.adresse = adresse;
        this.commandes = commandes;
        this.telephone=telephone;
        this.user=user;
    }
    public Client()
    {


    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
