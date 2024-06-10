package chatop.api.mappers.auth;

import chatop.api.models.entities.User;
import chatop.api.models.requests.auth.RegisterUserDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RegisterUserDTOMapper implements Function<User, RegisterUserDTO> {

    public RegisterUserDTO apply(@NotNull User user) {
        return RegisterUserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
