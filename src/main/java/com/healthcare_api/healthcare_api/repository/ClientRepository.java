package com.healthcare_api.healthcare_api.repository;


import com.healthcare_api.healthcare_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR CAST(c.id AS string) LIKE CONCAT('%', :query, '%')")
    List<Client> searchClients(String query);
}