package chatop.api.repository;

import chatop.api.models.entities.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalsRepository extends JpaRepository<Rentals, Integer> {
}