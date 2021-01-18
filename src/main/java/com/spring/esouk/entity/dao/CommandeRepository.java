package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Agriculteur;
import com.spring.esouk.entity.Client;
import com.spring.esouk.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Integer> {
    public Collection<Commande> findByClient(Client c);
    public List<Commande> findByAgriculteur(Agriculteur agr);

}
