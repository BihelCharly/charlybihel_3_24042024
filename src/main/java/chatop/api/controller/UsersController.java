package chatop.api.controller;

import chatop.api.models.entities.User;
import chatop.api.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name="USERS", description = "Operations for Users")
@RestController
@RequestMapping(path = "/auth")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "create", description = "Create a new user")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody User user) {
        this.usersService.register(user);
    }

    @Operation(summary = "get one", description = "Get one user by ID")
    @GetMapping(path = "/me", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User search(User user) {
        return this.usersService.search(user.getId());
    }

}
