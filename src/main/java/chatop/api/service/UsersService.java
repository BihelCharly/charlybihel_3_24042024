package chatop.api.service;

import chatop.api.models.entities.User;
import chatop.api.repository.IUsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    IUsersRepository iUsersRepository;

    public UsersService(IUsersRepository iUsersRepository) {
        this.iUsersRepository = iUsersRepository;
    }

    //public void register(RegisterRequest registerRequest) {
    //    Users usersExist = this.iUsersRepository.findByEmail(registerRequest.getEmail());
    //    if(usersExist== null) {
    //        registerRequest.setCreated_at(new Date());
    //        this.iUsersRepository.save(registerRequest);
    //    }
    //}

    public User search(Integer id) {
        return iUsersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec l'id: " + id));
    }

}
