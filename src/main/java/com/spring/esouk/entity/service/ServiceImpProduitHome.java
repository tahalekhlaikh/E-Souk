package com.spring.esouk.entity.service;

import com.spring.esouk.entity.*;
import com.spring.esouk.entity.dao.DaoImpProduitHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceImpProduitHome implements ServiceInterfaceProduitHome {

	@Autowired
	private DaoImpProduitHome dao;
	@Override
	@Transactional
	public List<Produit> getProduits() {
		// TODO Auto-generated method stub
		return dao.getProduits();
	}
	@Override
	public Produit searchProduit(String nom) {
		// TODO Auto-generated method stub
		return dao.searchProduit(nom);
	}
	@Override
	public Souk getSoukInTime() {
		// TODO Auto-generated method stub
		return dao.getSoukInTime();
	}
	@Override
	public List<Produit> getProduits(int id){
		return  dao.getProduits(id);
	}

	@Override
	public Boutique getBoutique(int id) {
		return dao.getBoutique(id);
	}

	@Override
	public List<Boutique> getBoutique() {
		return  dao.getBoutique();
	}

	@Override
	public List<Produitmettre> getProduitmettre() {
		return dao.getProduitmettre();
	}

	@Override
	public List<Produitmettre> getProduitmettre(int id) {
		return dao.getProduitmettre(id);
	}

	@Override
	public List<Produitmettre> getProduitmettre1(String produitNom) {
		return dao.getProduitmettre1(produitNom);
	}


}
