package ch.example.springgraphqlkeycloakboilerplate.repository;


import ch.example.springgraphqlkeycloakboilerplate.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMake(String make);
    
    List<Car> findByOwnerId(Long ownerId);
    
    Optional<Car> findByRegistrationNumber(String registrationNumber);
    
    @Query("SELECT c FROM Car c WHERE c.year >= :year")
    List<Car> findCarsNewerThan(Integer year);
}