package foreach.cda.recettes.services;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import foreach.cda.recettes.dtos.UserRequestDto;
import foreach.cda.recettes.dtos.UserResponseDto;
import foreach.cda.recettes.entities.Recettes;
import foreach.cda.recettes.entities.User;
import foreach.cda.recettes.mappers.UserMapper;
import foreach.cda.recettes.mappers.RecettesMapper;
import foreach.cda.recettes.repositories.UserRepository;
import foreach.cda.recettes.config.JwtUtil;
import foreach.cda.recettes.config.PasswordUtil;
import foreach.cda.recettes.dtos.RecettesResponseDto;
import foreach.cda.recettes.repositories.RecettesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RecettesRepository recettesRepository;
    private final RecettesMapper recettesMapper;

    public UserResponseDto createUser(UserRequestDto dto) {
    
    if (userRepository.findByMail(dto.getMail()) != null) {
        throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
    }

    User user = userMapper.toEntity(dto);
    user.setPassword(PasswordUtil.encode(dto.getPassword()));
    
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

    User existing = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));


    if (dto.getNom() != null) {
        existing.setNom(dto.getNom());
    }
    if (dto.getPrenom() != null) {
        existing.setPrenom(dto.getPrenom());
    }
    if (dto.getMail() != null) {
        existing.setMail(dto.getMail());
    }
    if (dto.getTelephone() != null) {
        existing.setTelephone(dto.getTelephone());
    }
    if (dto.getRole() != null) {
        existing.setRole(dto.getRole());
    }
    if (dto.getPassword() != null) {
        existing.setPassword(PasswordUtil.encode(dto.getPassword()));
    }

    User saved = userRepository.save(existing);

    return userMapper.toDTO(saved);
}

public void deleteUser(Integer id) {
    if (!userRepository.existsById(id)) {
        throw new RuntimeException("Utilisateur introuvable");
    }
    userRepository.deleteById(id);
}

//  gestion des favoris
public List<RecettesResponseDto> getFavorites(Integer userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    List<Recettes> fav = user.getFavoris();
    if (fav == null || fav.isEmpty()) {
        return List.of();
    }
    return fav.stream()
              .map(recettesMapper::toDTO)
              .toList();
}

public UserResponseDto addFavorite(Integer userId, Integer recetteId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    Recettes recette = recettesRepository.findById(recetteId)
            .orElseThrow(() -> new RuntimeException("Recette introuvable"));
    if (user.getFavoris() == null) {
        user.setFavoris(new java.util.ArrayList<>());
    }
    if (user.getFavoris().contains(recette)) {
        throw new RuntimeException("Recette déjà en favoris");
    }
    user.getFavoris().add(recette);
    User saved = userRepository.save(user);
    return userMapper.toDTO(saved);
}

public UserResponseDto removeFavorite(Integer userId, Integer recetteId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    Recettes recette = recettesRepository.findById(recetteId)
            .orElseThrow(() -> new RuntimeException("Recette introuvable"));
    if (user.getFavoris() == null || !user.getFavoris().remove(recette)) {
        throw new RuntimeException("Recette non présente dans les favoris");
    }
    User saved = userRepository.save(user);
    return userMapper.toDTO(saved);
}


    
    public foreach.cda.recettes.dtos.UserLoginResponseDto login(foreach.cda.recettes.dtos.UserLoginRequestDto dto) {
        User user = userRepository.findByMail(dto.getMail());
        if (user == null || !PasswordUtil.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Identifiants incorrects");
        }
        
        String token = JwtUtil.generateToken(user);
        foreach.cda.recettes.dtos.UserLoginResponseDto resp = new foreach.cda.recettes.dtos.UserLoginResponseDto();
        resp.setToken(token);
        resp.setIdUser(user.getIdUser());
        resp.setRole(user.getRole());
        resp.setMail(user.getMail());
        return resp;
    }
}

