package com.david.jpa_project.model.repositories.derived_queries;

import com.david.jpa_project.model.entities.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
