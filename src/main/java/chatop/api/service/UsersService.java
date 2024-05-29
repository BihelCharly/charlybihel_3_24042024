package chatop.api.service;

import chatop.api.models.entities.User;
import chatop.api.repository.IUsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    IUsersRepository iUsersRepository;

    public void register(User user) {
        Optional<User> usersExist = this.iUsersRepository.findByEmail(user.getEmail());
        if(usersExist.isEmpty()) {
            user.setCreatedAt(new Date());
            this.iUsersRepository.save(user);
        }
    }

    public User search(Integer id) {
        return iUsersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec l'id: " + id));
    }

}
