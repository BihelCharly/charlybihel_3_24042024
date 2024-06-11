package chatop.api.repository;

import chatop.api.models.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessagesRepository extends JpaRepository<Message, Integer> {
}