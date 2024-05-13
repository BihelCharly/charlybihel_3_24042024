package chatop.api.controller;

import chatop.api.models.entities.Messages;
import chatop.api.service.MessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name="MESSAGES", description = "Operations for Messages")
@RestController
@RequestMapping(path = "/messages")
public class MessagesController {

    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }
    @Operation(summary = "message", description = "Add a new message")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Messages createMessages(@RequestBody Messages messages) {
        return this.messagesService.createMessages(messages);
    }
}
