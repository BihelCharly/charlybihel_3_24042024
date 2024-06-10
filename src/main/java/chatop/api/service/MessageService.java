package chatop.api.service;

import chatop.api.repository.IMessagesRepository;
import chatop.api.repository.IRentalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagesService {

    IMessagesRepository iMessagesRepository;
    IRentalsRepository iRentalsRepository;

}
