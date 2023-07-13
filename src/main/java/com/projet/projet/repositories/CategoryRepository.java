package com.projet.projet.repositories;

import com.projet.projet.entities.Cart;
import com.projet.projet.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
    List<Categories> findByNameContaining(String name);
}

