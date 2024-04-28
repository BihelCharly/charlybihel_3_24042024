package chatop.api.service;

import chatop.api.repository.IMessagesRepository;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {

    IMessagesRepository iMessagesRepository;

    public MessagesService(IMessagesRepository iMessagesRepository) {
        this.iMessagesRepository = iMessagesRepository;
    }
}
