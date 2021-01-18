package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Boutique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoutiqueRepository extends CrudRepository<Boutique,Integer> {
    
}
