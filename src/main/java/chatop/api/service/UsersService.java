package chatop.api.service;

import chatop.api.entities.Users;
import chatop.api.repository.IUsersRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsersService {

    IUsersRepository iUsersRepository;

    public UsersService(IUsersRepository iUsersRepository) {
        this.iUsersRepository = iUsersRepository;
    }

    public void register(Users users) {
        Users usersExist = this.iUsersRepository.findByEmail(users.getEmail());
        if(usersExist== null) {
            users.setCreated_at(new Date());
            this.iUsersRepository.save(users);
        }
    }

    public Users search(Users users) {
        return this.iUsersRepository.findByEmail(users.getEmail());
    }

}
