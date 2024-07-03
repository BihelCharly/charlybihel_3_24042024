package chatop.api.controller;

import chatop.api.models.request.auth.LoginUserDTO;
import chatop.api.models.request.auth.RegisterUserDTO;
import chatop.api.models.response.EmptyResponse;
import chatop.api.models.response.TokenResponse;
import chatop.api.models.response.auth.AuthMeResponse;
import chatop.api.models.response.message.MessageResponse;
import chatop.api.models.response.user.UserResponseDTO;
import chatop.api.security.JwtService;
import chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Tag(name = "USERS", description = "Operations for Users")
@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    // TO REGISTER ONE NEW USER
    @Operation(summary = "register", description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TokenResponse.class)
            )),
            @ApiResponse(responseCode = "400", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
            ))
    })
    @PostMapping(path = "/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody RegisterUserDTO registerUserDTO) {
        this.userService.register(registerUserDTO);
    }


    // TO LOGIN ONE REGISTERED USER
    @Operation(summary = "login", description = "Login as a registered user")
    @ApiResponse( responseCode = "200", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TokenResponse.class)
    ))
    @ApiResponse( responseCode = "401", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = MessageResponse.class)
    ))
    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> login(@RequestBody LoginUserDTO loginUserDTO) {
        // TO GET EMAIL AND PASSWORD
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDTO.getLogin(), loginUserDTO.getPassword())
        );
        // IF USER EXIST -> CREATE JWT TOKEN
        if (authentication.isAuthenticated()) {
            return this.jwtService.generate(loginUserDTO.getLogin());
        }
        return null;
    }


    // TO GET LOGGED USER DETAILS
    @Operation(summary = "me", description = "Who's logged")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse( responseCode = "200", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AuthMeResponse.class)
    ))
    @ApiResponse( responseCode = "401", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = EmptyResponse.class)
    ))
    @GetMapping(path = "/auth/me")
    public UserResponseDTO getLoggedUserDetails(@AuthenticationPrincipal UserDetails userDetails) {

        return this.userService.getLoggedUserDetails(userDetails);

    }

    @Operation(summary = "get", description = "Get user by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponse( responseCode = "200", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UserResponseDTO.class)
    ))
    @ApiResponse( responseCode = "401", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = EmptyResponse.class)
    ))
    // TO GET USER DETAILS BY ID
    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO getOneUserById(@PathVariable int id) {

        return this.userService.getOneUserById(id);
    }

}
