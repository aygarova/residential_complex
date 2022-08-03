package com.example.copmprob.repository;

import com.example.copmprob.model.entity.Apartment;
import com.example.copmprob.model.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository  extends JpaRepository<Apartment,Long> {
    Apartment findApartmentByApartmentNumber(String apartmentNumber);

    Apartment findApartmentByTenantId(long tenant_id);

    List<Apartment> findApartmentByOwnerId(long owner_id);
    Apartment findUsersApartmentByTenantId(long owner_id);

    List<Apartment> findApartmentByStatus(StatusEnum statusEnum);
}
