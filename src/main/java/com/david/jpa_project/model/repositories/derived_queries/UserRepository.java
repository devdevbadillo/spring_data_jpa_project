package com.david.jpa_project.model.repositories.derived_queries;

import com.david.jpa_project.model.entities.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
