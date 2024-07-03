package chatop.api.controller;

import chatop.api.models.request.messages.CreateMessageDTO;
import chatop.api.models.response.EmptyResponse;
import chatop.api.models.response.StringResponse;
import chatop.api.models.response.TokenResponse;
import chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse( responseCode = "200", description = "OK", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TokenResponse.class)
    ))
    @ApiResponse( responseCode = "400", description = "Bad request", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = EmptyResponse.class )
    ))
    @ApiResponse( responseCode = "401", description = "Unauthorized", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = EmptyResponse.class)
    ))
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StringResponse postOneMessage(@Valid @RequestBody CreateMessageDTO createMessageDTO) {

        return this.messageService.postOneMessage(createMessageDTO);

    }

}
