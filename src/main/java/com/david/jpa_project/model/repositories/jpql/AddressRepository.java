package com.david.jpa_project.model.repositories.jpql;

import com.david.jpa_project.model.entities.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.city = :city")
    Page<Address> findAddressesByCity(String city, Pageable pageable);

    @Query("SELECT a FROM Address a WHERE a.country LIKE %:country%")
    Page<Address> findAddressesByCountryLike(String country, Pageable pageable);
}
