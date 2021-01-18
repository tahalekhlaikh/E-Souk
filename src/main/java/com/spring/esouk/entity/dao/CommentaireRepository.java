package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Agriculteur;
import com.spring.esouk.entity.Commentaire;
import com.spring.esouk.entity.Produitmettre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
    public List<Commentaire> findByProduitmettre(Produitmettre produit);
}
