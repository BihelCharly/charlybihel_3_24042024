package chatop.api.service;

import chatop.api.repository.IMessagesRepository;
import chatop.api.repository.IRentalsRepository;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {

    IMessagesRepository iMessagesRepository;
    IRentalsRepository iRentalsRepository;

    public MessagesService(IMessagesRepository iMessagesRepository, IRentalsRepository iRentalsRepository) {
        this.iMessagesRepository = iMessagesRepository;
        this.iRentalsRepository = iRentalsRepository;
    }

}
