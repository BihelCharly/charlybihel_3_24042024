package chatop.api.controller;

import chatop.api.entities.Messages;
import chatop.api.service.MessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/messages")
public class MessagesController {

    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    // je sais qu'il faut aussi que je check l'user_id mais actuellement ça ne fonctionne tout de meme pas
    // pourtant je ne vois pas ce qu'il manque
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Messages createMessages(@RequestBody Messages messages) {
        return this.messagesService.createMessages(messages);
    }
}
