package chatop.api.service;

import chatop.api.repository.IMessageRepository;
import chatop.api.repository.IRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    IMessageRepository iMessageRepository;
    IRentalRepository iRentalRepository;

}
