package chatop.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@Tag(name = "MESSAGES", description = "Operations for Messages")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/messages")
public class MessageController {

}
