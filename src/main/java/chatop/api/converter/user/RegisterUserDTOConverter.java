package chatop.api.converters.user;

import chatop.api.models.entities.User;
import chatop.api.models.requests.auth.RegisterUserDTO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterUserDTOConverter implements Converter<RegisterUserDTO, User> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User convert(@NotNull RegisterUserDTO registerUserDTO) {
        return User.builder()
                .email(registerUserDTO.getEmail())
                .name(registerUserDTO.getName())
                .password(registerUserDTO.getPassword())
                .build();
    }
}
