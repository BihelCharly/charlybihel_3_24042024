package chatop.api.service;

import chatop.api.models.entities.User;
import chatop.api.repository.IUsersRepository;
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

    public User search(User user) {
        return this.iUsersRepository.findByEmail(user.getEmail());
    }

}
