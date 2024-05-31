package chatop.api.service;

import chatop.api.models.entities.Role;
import chatop.api.models.entities.User;
import chatop.api.models.enums.RoleType;
import chatop.api.repository.IUsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final IUsersRepository iUsersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(User user) {
        Optional<User> usersExist = this.iUsersRepository.findByEmail(user.getEmail());
        if (usersExist.isEmpty()) {
            // CREATE NEW ROLE
            Role roleUser = new Role();
            roleUser.setStatus(RoleType.USER);
            user.setRole(roleUser);
            // ENCRYPT PASSWORD
            String encryptedPassword = this.bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            // SET CREATED DATE
            user.setCreatedAt(new Date());
            // TO GENERATE PUBLIC ID
            // UUID.randomUUID();
            this.iUsersRepository.save(user);
        } else {
            throw new RuntimeException("This user already exist !");
        }
    }

    public User search(Integer id) {
        return iUsersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec l'id: " + id));
    }

}
