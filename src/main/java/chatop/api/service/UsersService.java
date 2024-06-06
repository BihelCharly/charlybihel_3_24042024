package chatop.api.service;

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

    private final IUsersRepository iUsersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegisterUserDTO registerUserDTO) {
        Optional<User> usersExist = this.iUsersRepository.findByEmail(registerUserDTO.getEmail());
        if (usersExist.isEmpty()) {
            // CREATE NEW ROLE
            Role roleUser = new Role();
            roleUser.setStatus(RoleType.USER);
            User newUser = new User();
            newUser.setRole(roleUser);
            // ENCRYPT PASSWORD
            String encryptedPassword = this.bCryptPasswordEncoder.encode(registerUserDTO.getPassword());
            newUser.setPassword(encryptedPassword);
            // SET CREATED DATE
            newUser.setCreatedAt(new Date());
            // TO GENERATE PUBLIC ID
            // UUID.randomUUID();
            this.iUsersRepository.save(newUser);
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
