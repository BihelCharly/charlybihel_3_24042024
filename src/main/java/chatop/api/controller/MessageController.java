package chatop.api.controller;

import chatop.api.models.request.messages.CreateMessageDTO;
import chatop.api.models.response.StringResponse;
import chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MESSAGES", description = "Operations for Messages")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessageController {

    private final MessageService messageService;

    // TO CREATE ONE MESSAGE
    @Operation(summary = "post message", description = "Post one new message")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StringResponse postOneMessage(@Valid @RequestBody CreateMessageDTO createMessageDTO) {

        return this.messageService.postOneMessage(createMessageDTO);

    }

}
