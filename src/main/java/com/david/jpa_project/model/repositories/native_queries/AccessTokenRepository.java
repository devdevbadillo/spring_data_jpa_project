package com.david.jpa_project.model.repositories.native_queries;

import com.david.jpa_project.model.entities.entity.AccessToken;
import com.david.jpa_project.model.entities.enums.AccessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {

    @Query(value= "SELECT * FROM T_ACCESS_TOKEN WHERE jwt_id = ?1", nativeQuery = true)
    AccessToken findByJwtId(String jwtId);

    @Query(value= "SELECT * FROM T_ACCESS_TOKEN WHERE access_type = ?1 AND created_at = ?2", nativeQuery = true)
    List<AccessToken> findAccessTokenByAccessTypeAndAuditData_CreatedAt(AccessType accessType, Date auditDataCreatedAt);
}
