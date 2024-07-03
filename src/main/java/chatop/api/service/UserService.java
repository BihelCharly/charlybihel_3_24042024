package chatop.api.service;

import chatop.api.converter.user.RegisterUserDTOConverter;
import chatop.api.models.entity.User;
import chatop.api.models.request.auth.RegisterUserDTO;
import chatop.api.models.response.user.UserResponseDTO;
import chatop.api.repository.IUserRepository;
import chatop.api.security.JwtService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final IUserRepository iUserRepository;
    private final RegisterUserDTOConverter registerUserDTOConverter;

    @Autowired
    ModelMapper modelMapper;

    public Boolean register(RegisterUserDTO registerUserDTO) {
        Optional<User> usersExist = this.iUserRepository.findByEmail(registerUserDTO.getEmail());
        if (usersExist.isEmpty()) {
            User newUser = registerUserDTOConverter.convert(registerUserDTO);
            if (newUser != null) {
                newUser.setCreatedAt(new Date());
                iUserRepository.save(newUser);
                return true;
            }
        }
        return false;
    }


    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.iUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Sorry but we didn't find any user with this email !"));
    }

    public UserResponseDTO getLoggedUserDetails(UserDetails userDetails) {
        return modelMapper.map(userDetails, UserResponseDTO.class);
    }

    public UserResponseDTO getOneUserById(int id) {
        Optional<User> optionalUser = this.iUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            return modelMapper.map(optionalUser, UserResponseDTO.class);
        } else {
            return null;
        }
    }

}