package chatop.api.converter.user;

import chatop.api.models.entity.Role;
import chatop.api.models.entity.User;
import chatop.api.models.enums.RoleType;
import chatop.api.models.request.auth.RegisterUserDTO;
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
        Role roleUser = new Role();
        roleUser.setStatus(RoleType.USER);
        return User.builder()
                .role(roleUser)
                .active(true)
                .email(registerUserDTO.getEmail())
                .name(registerUserDTO.getName())
                .password(bCryptPasswordEncoder.encode(registerUserDTO.getPassword()))
                .build();
    }
}
