package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Agriculteur;
import com.spring.esouk.entity.Produitmettre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitmettreRepository extends JpaRepository<Produitmettre,Integer> {

    @Query(value="Select * From Produitmettre where id_agriculteur = ?",nativeQuery=true)
    public List<Produitmettre> findById1(int username);
    public List<Produitmettre> findByAgriculteur(Agriculteur agr);
}
