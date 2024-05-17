package chatop.api.repository;

import chatop.api.models.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalsRepository extends JpaRepository<Rental, Integer> {
}
