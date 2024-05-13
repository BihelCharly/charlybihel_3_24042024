package chatop.api.service;

import chatop.api.models.entities.Users;
import chatop.api.models.requests.auth.RegisterRequest;
import chatop.api.repository.IUsersRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public Users search(Users users) {
        return this.iUsersRepository.findByEmail(users.getEmail());
    }

}
