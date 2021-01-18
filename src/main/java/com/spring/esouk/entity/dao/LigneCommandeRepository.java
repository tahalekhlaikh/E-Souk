package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Agriculteur;
import com.spring.esouk.entity.LigneCommande;
import com.spring.esouk.entity.Produitmettre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Integer> {
    public LigneCommande findByProduitmettre(Produitmettre pr);
    public Collection<LigneCommande> findByProduitmettre_Agriculteur(Agriculteur agr);

}
