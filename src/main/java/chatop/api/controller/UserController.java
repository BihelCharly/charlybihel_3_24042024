package chatop.api.controller;

import chatop.api.models.request.auth.LoginUserDTO;
import chatop.api.models.request.auth.RegisterUserDTO;
import chatop.api.models.response.GetUserResponseDTO;
import chatop.api.security.JwtService;
import chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "register", description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(path = "/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody RegisterUserDTO registerUserDTO) {
        this.userService.register(registerUserDTO);
    }

    // TO LOGIN ONE REGISTERED USER
    @Operation(summary = "login", description = "Login as a registered user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping(path = "/auth/me")
    public GetUserResponseDTO getLoggedUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return this.userService.getLoggedUserDetails(userDetails);
    }

    // TO GET USER DETAILS BY ID
    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetUserResponseDTO getOneUserById(@PathVariable int id) {
        return this.userService.getOneUserById(id);
    }

}
