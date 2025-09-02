package com.david.jpa_project.model.repositories.native_queries;

import com.david.jpa_project.model.entities.embeddable.RefreshTokenId;
import com.david.jpa_project.model.entities.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, RefreshTokenId> {

}
