package chatop.api.service;

import chatop.api.mappers.auth.RegisterUserDTOMapper;
import chatop.api.models.entities.Role;
import chatop.api.models.entities.User;
import chatop.api.models.enums.RoleType;
import chatop.api.models.requests.auth.RegisterUserDTO;
import chatop.api.repository.IUsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersService implements UserDetailsService {

    private final RegisterUserDTOMapper registerUserDTOMapper;
    private final IUsersRepository iUsersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegisterUserDTO registerUserDTO) {
        Optional<User> usersExist = this.iUsersRepository.findByEmail(registerUserDTO.getEmail());
        if (usersExist.isEmpty()) {
            // CREATE NEW ROLE
            Role roleUser = new Role();
            roleUser.setStatus(RoleType.USER);
            String encryptedPassword = this.bCryptPasswordEncoder.encode(registerUserDTO.getPassword());

            User newUser = new User();

            newUser.setRole(roleUser);
            newUser.setPassword(encryptedPassword);
            newUser.setCreatedAt(new Date());
            this.iUsersRepository.save(newUser);
            // UUID.randomUUID();
        } else {
            throw new RuntimeException("This user already exist !");
        }
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.iUsersRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Sorry but we didn't find any user with this email !"));
    }
}
