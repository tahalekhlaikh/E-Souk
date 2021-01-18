package com.spring.esouk.entity;

import java.io.Serializable;

public class Register implements Serializable {

    private String username;
    private String password;
    private String repassword;
    private String type;
    private String photo;
    private String nom;
    private String prenom;
    private int telephone;
    private String adresse;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Register(String username, String password, String repassword, String type, String photo, String nom, String prenom, int telephone) {
        this.username = username;
        this.password = password;
        this.repassword = repassword;
        this.type = type;
        this.photo = photo;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Register() {
    }
}
