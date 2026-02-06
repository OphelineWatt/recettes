package foreach.cda.recettes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import foreach.cda.recettes.dtos.UserDTO;
import foreach.cda.recettes.entities.User;
import foreach.cda.recettes.mappers.UserMapper;
import foreach.cda.recettes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

     private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return userMapper.toDTO(users);
    }

    public UserDTO find(Integer id){
        User user = null;
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
        }
        return userMapper.toDTO(user);
    }

    public void update(Integer id, UserRequestDTO user){
        User userToUpdate = userMapper.toEntity(user);
        userToUpdate.setIdUser(id);
        userRepository.save(userToUpdate);
    }

    
    public void save(UserRequestDTO user){
        userRepository.save(userMapper.toEntity(user));
    }

    public void remove(Integer id){
        userRepository.deleteById(id);
    }
    
}
