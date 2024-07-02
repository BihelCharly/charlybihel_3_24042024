package chatop.api.converter.message;

import chatop.api.models.entity.Message;
import chatop.api.models.request.messages.CreateMessageDTO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMessageDTOConverter implements Converter<CreateMessageDTO, Message> {

    public Message convert(@NotNull CreateMessageDTO createMessageDTO) {
        return Message.builder()
                .message(createMessageDTO.getMessage())
                .userId(createMessageDTO.getUserId())
                .rentalId(createMessageDTO.getRentalId())
                .build();
    }
}
