package chatop.api.service;

import chatop.api.converter.message.CreateMessageDTOConverter;
import chatop.api.models.entity.Message;
import chatop.api.models.entity.Rental;
import chatop.api.models.entity.User;
import chatop.api.models.request.messages.CreateMessageDTO;
import chatop.api.models.response.StringResponse;
import chatop.api.repository.IMessageRepository;
import chatop.api.repository.IRentalRepository;
import chatop.api.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final CreateMessageDTOConverter createMessageDTOConverter;
    private final IRentalRepository iRentalRepository;
    private final IUserRepository iUserRepository;
    private final IMessageRepository iMessageRepository;

    public StringResponse postOneMessage(CreateMessageDTO createMessageDTO) {
        Optional<Rental> optionalRental = this.iRentalRepository.findById(createMessageDTO.getRentalId());
        Optional<User> optionalUser = this.iUserRepository.findById(createMessageDTO.getUserId());
        if (optionalRental.isPresent() && optionalUser.isPresent()) {
            Message newMessage = createMessageDTOConverter.convert(createMessageDTO);
            if (newMessage != null) {
                newMessage.setCreatedAt(new Date());
                iMessageRepository.save(newMessage);
                return StringResponse.builder().message("Message send with success !").build();
            }
        }
        return null;
    }

}
