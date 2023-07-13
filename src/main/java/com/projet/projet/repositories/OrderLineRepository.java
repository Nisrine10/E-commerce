package com.projet.projet.repositories;

import com.projet.projet.entities.Order;
import com.projet.projet.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {



}
