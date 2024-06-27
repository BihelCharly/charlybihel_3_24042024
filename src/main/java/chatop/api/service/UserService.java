package chatop.api.service;

import chatop.api.converter.user.RegisterUserDTOConverter;
import chatop.api.models.entity.User;
import chatop.api.models.request.auth.RegisterUserDTO;
import chatop.api.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final IUserRepository iUserRepository;
    private final RegisterUserDTOConverter registerUserDTOConverter;

    public void register(RegisterUserDTO registerUserDTO) {
        Optional<User> usersExist = this.iUserRepository.findByEmail(registerUserDTO.getEmail());
        if (usersExist.isEmpty()) {
            User newUser = registerUserDTOConverter.convert(registerUserDTO);
            if (newUser != null) {
                newUser.setCreatedAt(new Date());
                iUserRepository.save(newUser);
                //return UserResponse.builder().message("User created !").build();
            }
        } else {
            throw new RuntimeException("This user already exist !");
        }
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.iUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Sorry but we didn't find any user with this email !"));
    }
}


// POUR LE /me de la route auth
// retourner dans un objet :
// id, name, email, createdAt, updateAt