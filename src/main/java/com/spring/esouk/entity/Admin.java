package com.spring.esouk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Admin {
    @Id
    private String nom;
    private String prenom;
    @OneToOne
    private User user;



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

    public Admin() {
    }
    public Admin(String nom,String Prenom,User user)
    {

        this.nom=nom;
        this.prenom=Prenom;
        this.user=user;
    }
}
