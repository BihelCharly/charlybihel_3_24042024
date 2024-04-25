package chatop.api.service;

import chatop.api.entities.Users;
import chatop.api.repository.IUsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UsersService {

    IUsersRepository iUsersRepository;

    public UsersService(IUsersRepository iUsersRepository) {
        this.iUsersRepository = iUsersRepository;
    }

    public void register(Users users) {
        Users usersExist = this.iUsersRepository.findByEmail(users.getEmail());
        if(usersExist== null) {
        this.iUsersRepository.save(users);}
    }

    public Users search(Users users) {
        return this.iUsersRepository.findByEmail(users.getEmail());
    }

}
