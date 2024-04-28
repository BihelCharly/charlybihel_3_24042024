package chatop.api.service;

import chatop.api.entities.Messages;
import chatop.api.entities.Rentals;
import chatop.api.repository.IMessagesRepository;
import chatop.api.repository.IRentalsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessagesService {

    IMessagesRepository iMessagesRepository;
    IRentalsRepository iRentalsRepository;

    public MessagesService(IMessagesRepository iMessagesRepository, IRentalsRepository iRentalsRepository) {
        this.iMessagesRepository = iMessagesRepository;
        this.iRentalsRepository = iRentalsRepository;
    }


    public void createMessages(Messages messages) {
        Optional<Rentals> optionalRentals = this.iRentalsRepository.findById(messages.getRental_id());
        if(optionalRentals.isPresent()) {
            this.iMessagesRepository.save(messages);
        }
    }
}
