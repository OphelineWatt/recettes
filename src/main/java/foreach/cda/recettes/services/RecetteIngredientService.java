package foreach.cda.recettes.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import foreach.cda.recettes.dtos.RecetteIngredientRequestDto;
import foreach.cda.recettes.dtos.RecetteIngredientResponseDto;
import foreach.cda.recettes.entities.RecetteIngredient;
import foreach.cda.recettes.entities.Recettes;
import foreach.cda.recettes.entities.Ingredient;
import foreach.cda.recettes.mappers.RecetteIngredientMapper;
import foreach.cda.recettes.repositories.RecetteIngredientRepository;
import foreach.cda.recettes.repositories.RecettesRepository;
import foreach.cda.recettes.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecetteIngredientService {

    private final RecetteIngredientRepository riRepository;
    private final RecettesRepository recettesRepository;
    private final IngredientRepository ingredientRepository;
    private final RecetteIngredientMapper mapper;

    public RecetteIngredientResponseDto create(RecetteIngredientRequestDto dto) {
        Recettes recette = recettesRepository.findById(dto.getRecetteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recette introuvable"));
        Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrédient introuvable"));
        RecetteIngredient existing = riRepository.findByRecetteAndIngredient(recette, ingredient);
        if (existing != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette recette contient déjà cet ingrédient");
        }
        RecetteIngredient entity = mapper.toEntity(dto);
        entity.setRecette(recette);
        entity.setIngredient(ingredient);
        RecetteIngredient saved = riRepository.save(entity);
        return mapper.toDTO(saved);
    }

    public List<RecetteIngredientResponseDto> findAll() {
        return riRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    public RecetteIngredientResponseDto findById(Integer id) {
        RecetteIngredient ri = riRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Association recette/ingrédient introuvable"));
        return mapper.toDTO(ri);
    }

    public RecetteIngredientResponseDto update(Integer id, RecetteIngredientRequestDto dto) {
        if (!riRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Association recette/ingrédient introuvable");
        }
        Recettes recette = recettesRepository.findById(dto.getRecetteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recette introuvable"));
        Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrédient introuvable"));

        RecetteIngredient entity = mapper.toEntity(dto);
        entity.setId(id);
        entity.setRecette(recette);
        entity.setIngredient(ingredient);
        RecetteIngredient saved = riRepository.save(entity);
        return mapper.toDTO(saved);
    }

    public void delete(Integer id) {
        if (!riRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Association recette/ingrédient introuvable");
        }
        riRepository.deleteById(id);
    }

    public List<RecetteIngredientResponseDto> findByRecette(Integer recetteId) {
        Recettes recette = recettesRepository.findById(recetteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recette introuvable"));
        return riRepository.findByRecette(recette).stream().map(mapper::toDTO).toList();
    }

    public List<RecetteIngredientResponseDto> findByIngredient(Integer ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrédient introuvable"));
        return riRepository.findByIngredient(ingredient).stream().map(mapper::toDTO).toList();
    }
}
