package com.david.jpa_project.model.repositories.pagination_and_sort;

import com.david.jpa_project.model.entities.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
