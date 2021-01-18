package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query(value="Select * From Client c where c.user_username= ?",nativeQuery=true)
    public Client findByUserName(String username);
}
