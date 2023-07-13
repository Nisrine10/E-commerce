package com.projet.projet.repositories;

import com.projet.projet.entities.Cart;
import com.projet.projet.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByClient(Client client);
}
