package chatop.api.service;

import chatop.api.models.entities.Messages;
import chatop.api.models.entities.Rentals;
import chatop.api.repository.IMessagesRepository;
import chatop.api.repository.IRentalsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MessagesService {

    IMessagesRepository iMessagesRepository;
    IRentalsRepository iRentalsRepository;

    public MessagesService(IMessagesRepository iMessagesRepository, IRentalsRepository iRentalsRepository) {
        this.iMessagesRepository = iMessagesRepository;
        this.iRentalsRepository = iRentalsRepository;
    }

}
