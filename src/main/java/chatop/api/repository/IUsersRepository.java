package chatop.api.repository;

import chatop.api.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
