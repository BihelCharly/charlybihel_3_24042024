package chatop.api.controller;

import chatop.api.models.entities.Message;
import chatop.api.service.MessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name="MESSAGES", description = "Operations for Messages")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessagesController {

}
