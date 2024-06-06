package chatop.api.controller;

import chatop.api.models.entities.User;
import chatop.api.models.requests.auth.LoginUserDTO;
import chatop.api.security.JwtService;
import chatop.api.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Tag(name = "USERS", description = "Operations for Users")
@AllArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class UsersController {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Operation(summary = "register", description = "Create a new user")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody User user) {
        this.usersService.register(user);
    }

    //@Operation(summary = "login", description = "Login as a registered user")
    //@PostMapping(path = "/login")
    //public Map<String, String> login(@RequestBody LoginUserDTO loginUserDTO) {
    //    final Authentication authenticate = authenticationManager.authenticate(
    //            new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword())
    //    );
    //    log.info("result {}", authenticate.isAuthenticated());
    //    return null;
    //}

    @Operation(summary = "login", description = "Login as a registered user")
    @PostMapping(path = "/login")
    public Map<String, String> login(@RequestBody LoginUserDTO loginUserDTO) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return this.jwtService.generate(loginUserDTO.getEmail());
        }
        return null;
    }

}
