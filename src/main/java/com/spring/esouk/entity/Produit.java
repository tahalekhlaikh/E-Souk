package com.spring.esouk.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="produit")
public class Produit implements Serializable {
	
@Id
@GeneratedValue
@Column(name="id")
private int id;

@Column(name="designation")
private String designation;

@Column(name="prix")
private double prix;



@Column(name="photo")
private String photo;

@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
@JoinColumn(name="categorie")
private Categorie categorie;





	public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public double getPrix() {
	return prix;
}

public void setPrix(double prix) {
	this.prix = prix;
}



public String getPhoto() {
	return photo;
}

public void setPhoto(String photo) {
	this.photo = photo;
}

public Categorie getCategorie() {
	return categorie;
}

public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

}
