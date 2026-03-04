package foreach.cda.recettes.services;
import java.util.List;

import org.springframework.stereotype.Service;

import foreach.cda.recettes.dtos.UserRequestDto;
import foreach.cda.recettes.dtos.UserResponseDto;
import foreach.cda.recettes.entities.User;
import foreach.cda.recettes.mappers.UserMapper;
import foreach.cda.recettes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto createUser(UserRequestDto dto) {
    
    if (userRepository.findByMail(dto.getMail()) != null) {
        throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
    }

    User user = userMapper.toEntity(dto);
    
    User saved = userRepository.save(user);

    return userMapper.toDTO(saved);
}


public List<UserResponseDto> findAll() {
    List<User> users = userRepository.findAll();
    return users.stream()
                .map(userMapper::toDTO)
                .toList();
}

public UserResponseDto findById(Integer id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    return userMapper.toDTO(user);
}

public UserResponseDto updateUser(Integer id, UserRequestDto dto) {

    if(userRepository.findById(id) == null) {
        throw new RuntimeException("Utilisateur introuvable");
    }

    User updated = userMapper.toEntity(dto);
    updated.setIdUser(id);

    User saved = userRepository.save(updated);

    return userMapper.toDTO(saved);
}

public void deleteUser(Integer id) {
    if (!userRepository.existsById(id)) {
        throw new RuntimeException("Utilisateur introuvable");
    }
    userRepository.deleteById(id);
}



}
