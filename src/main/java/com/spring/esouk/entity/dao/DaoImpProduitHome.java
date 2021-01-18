package com.spring.esouk.entity.dao;


import com.spring.esouk.entity.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DaoImpProduitHome implements DaoInterfaceProduitHome {

	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public List<Produit> getProduits() {
		// TODO Auto-generated method stub
		
		
		Query<Produit> query=(Query<Produit>) entityManager.createQuery("from Produit",Produit.class);
		 return query.getResultList();
		
				}
	@Override
	public List<Produit> getProduits(int id)
	{
		Query<Produit> query=(Query<Produit>) entityManager.createQuery("from Produit where categorie="+id,Produit.class);
		return query.getResultList();
	}
	@Override
	public Produit searchProduit(String nom) {
		// TODO Auto-generated method stub
		Query<Produit> theQuery=null;
		if (nom != null && nom.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			 theQuery=(Query<Produit>) entityManager.createQuery("from Produit where lower(designation) like :theName ", Produit.class);
			theQuery.setParameter("theName", "%" + nom.toLowerCase() + "%");

			}
			else {
			// theSearchName is empty ... so just get all customers
			 theQuery =(Query<Produit>) entityManager.createQuery("from Produit", Produit.class);
			}
		return theQuery.getSingleResult();
	}
	@Override
	public Souk getSoukInTime() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boutique getBoutique(int id)
	{
		Query<Boutique> theQuery =(Query<Boutique>) entityManager.createQuery("from Boutique where id="+id, Boutique.class);


		return theQuery.getSingleResult();


	}

	@Override
	public List<Boutique> getBoutique() {
		Query<Boutique> theQuery =(Query<Boutique>) entityManager.createQuery("from Boutique", Boutique.class
		);
		return theQuery.getResultList();
	}
	@Override
	public List<Produitmettre> getProduitmettre()
	{
		Query<Produitmettre> theQuery =(Query<Produitmettre>) entityManager.createQuery("from Produitmettre", Produitmettre.class
		);
		return theQuery.getResultList();
	}
	@Override
	public List<Produitmettre> getProduitmettre(int id)
	{
		Query<Produitmettre> theQuery =(Query<Produitmettre>) entityManager.createQuery("from Produitmettre where id_agriculteur.boutique. ="+id, Produitmettre.class
		);
		return theQuery.getResultList();
	}
	@Override
	public List<Produitmettre> getProduitmettre1(String produitNom)
	{
		Query<Produitmettre> theQuery =(Query<Produitmettre>) entityManager.createQuery("from Produitmettre where produit.designation= :produit", Produitmettre.class
		);
		theQuery.setParameter("produit",produitNom);
		return theQuery.getResultList();
	}


}
