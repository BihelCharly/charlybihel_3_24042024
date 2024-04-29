package chatop.api.controller;

import chatop.api.entities.Users;
import chatop.api.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/auth")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody Users users) {
        this.usersService.register(users);
    }

    @GetMapping(path = "/me", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users search(Users users) {
        return this.usersService.search(users);
    }

}
