package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Agriculteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgriculteurRepository extends JpaRepository<Agriculteur, Integer> {

    Agriculteur findById(int id);
    @Query(value="Select * From Agriculteur c where c.username= ?",nativeQuery=true)
    public Agriculteur findByUserName(String username);
    public Agriculteur findByNomContains(String recherche);
    public Agriculteur findByNomLike(String recherche);

}