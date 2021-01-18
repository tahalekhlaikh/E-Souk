package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produit,Integer> {
}
