package com.spring.esouk.entity.service;

import com.spring.esouk.entity.*;

import java.util.List;

public interface ServiceInterfaceProduitHome {

	public List<Produit> getProduits();

	public Produit searchProduit(String nom);

	public Souk getSoukInTime();
	public List<Produit> getProduits(int id);
	public Boutique getBoutique(int id);
	public List<Boutique> getBoutique();
	public List<Produitmettre> getProduitmettre();
	public List<Produitmettre> getProduitmettre(int id);
	public List<Produitmettre> getProduitmettre1(String produitNom);
}
