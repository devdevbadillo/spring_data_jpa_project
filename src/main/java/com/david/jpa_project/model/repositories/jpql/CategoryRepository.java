package com.david.jpa_project.model.repositories.jpql;

import com.david.jpa_project.model.entities.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
    List<Category> findCategoryByNameLike(String name);

    @Query("SELECT c FROM Category c WHERE c.auditData.createdAt BETWEEN :start AND :end")
    List<Category> findCategoriesByAuditData_CreatedAtBetween(Date start, Date end);

}
