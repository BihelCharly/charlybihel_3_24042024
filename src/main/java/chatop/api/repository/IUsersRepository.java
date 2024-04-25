package chatop.api.repository;

import chatop.api.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

}
