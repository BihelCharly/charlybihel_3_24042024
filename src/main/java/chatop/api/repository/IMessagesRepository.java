package chatop.api.repository;

import chatop.api.models.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessagesRepository extends JpaRepository<Messages, Integer> {
}
