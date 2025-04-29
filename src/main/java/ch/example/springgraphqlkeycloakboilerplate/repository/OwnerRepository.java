package ch.example.springgraphqlkeycloakboilerplate.repository;


import ch.example.springgraphqlkeycloakboilerplate.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByEmail(String email);
    
    @Query("SELECT o FROM Owner o WHERE SIZE(o.cars) > 0")
    List<Owner> findOwnersWithCars();
}