package chatop.api.repository;

import chatop.api.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}