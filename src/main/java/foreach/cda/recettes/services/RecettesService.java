package foreach.cda.recettes.services;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import foreach.cda.recettes.dtos.RecettesRequestDto;
import foreach.cda.recettes.dtos.RecettesResponseDto;
import foreach.cda.recettes.entities.Recettes;
import foreach.cda.recettes.entities.User;
import foreach.cda.recettes.mappers.RecettesMapper;
import foreach.cda.recettes.repositories.RecettesRepository;
import foreach.cda.recettes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecettesService {

    private final RecettesRepository recettesRepository;
    private final RecettesMapper recettesMapper;
    private final UserRepository userRepository;

    public RecettesResponseDto createRecettes(RecettesRequestDto dto, Integer userId) {

    Recettes recette = recettesMapper.toEntity(dto);

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    recette.setUser(user);
    Recettes saved = recettesRepository.save(recette);

    return recettesMapper.toDTO(saved);
}


public List<RecettesResponseDto> findAll() {
    List<Recettes> recettes = recettesRepository.findAll();
    return recettes.stream()
                .map(recettesMapper::toDTO)
                .toList();
}

public RecettesResponseDto findById(Integer id) {
    Recettes recette = recettesRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recette introuvable"));
    return recettesMapper.toDTO(recette);
}

public RecettesResponseDto updateRecettes(Integer id, Integer idUser, RecettesRequestDto dto) {

    if(recettesRepository.findById(id) == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recette introuvable");
    }

    Recettes updated = recettesMapper.toEntity(dto);
    updated.setIdRecette(id);

     User user = userRepository.findById(idUser)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

    updated.setUser(user);

    Recettes saved = recettesRepository.save(updated);

    return recettesMapper.toDTO(saved);
}

public void deleteRecette(Integer id) {
    if (!recettesRepository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recette introuvable");
    }
    recettesRepository.deleteById(id);
}



}
