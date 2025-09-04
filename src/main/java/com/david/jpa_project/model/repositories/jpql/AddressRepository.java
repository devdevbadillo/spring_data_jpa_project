package com.david.jpa_project.model.repositories.jpql;

import com.david.jpa_project.model.entities.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.city = :city")
    List<Address> findAddressesByCity(String city);

    @Query ("SELECT COUNT(*) FROM Address a WHERE a.state =:state AND a.city = :city")
    long countByStateAndCity(String city);

    @Query("SELECT a FROM Address a WHERE a.country LIKE %:country%")
    List<Address> findAddressesByCountryLike(String country);
}
