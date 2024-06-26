package chatop.api.repository;

import chatop.api.models.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRentalRepository extends JpaRepository<Rental, Integer> {

    Optional<Rental> findById(int rental_id);

    Optional<Rental> findByName(String name);

}
