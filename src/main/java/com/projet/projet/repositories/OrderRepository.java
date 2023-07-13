package com.projet.projet.repositories;

import com.projet.projet.entities.Client;
import com.projet.projet.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClient(Client client);


    List<Order> findAll();
}
