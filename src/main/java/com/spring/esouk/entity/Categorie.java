package com.spring.esouk.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="categorie")
public class Categorie implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="nom")
	private String nom;
	@Column(name="photo")
	private String photo;
	@Column(name="description")
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Categorie() {
	}
	public Categorie(int id,String nom,String photo,String description)
	{
		this.id=id;
		this.nom=nom;
		this.photo=photo;
		this.description=description;
	}
}
