package chatop.api.repository;

import chatop.api.models.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRentalsRepository extends JpaRepository<Rental, Integer> {
    Optional<Rental> findById(int rental_id);

    Optional<Rental> findByName(String name);
}
