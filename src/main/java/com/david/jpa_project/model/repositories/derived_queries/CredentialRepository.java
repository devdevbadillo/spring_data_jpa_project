package com.david.jpa_project.model.repositories.derived_queries;

import com.david.jpa_project.model.entities.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findCredentialByEmail(String email);

    Integer countCredentialByIsEnabledTrue();

    Boolean existsByEmail(String email);

    List<Credential> findCredentialsByEmailContaining(String pattern);
}
