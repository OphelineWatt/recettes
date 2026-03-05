package foreach.cda.recettes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import foreach.cda.recettes.dtos.IngredientRequestDto;
import foreach.cda.recettes.dtos.IngredientResponseDto;
import foreach.cda.recettes.entities.Ingredient;
import foreach.cda.recettes.mappers.IngredientMapper;
import foreach.cda.recettes.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientResponseDto createIngredient(IngredientRequestDto dto) {
        List<Ingredient> existing = ingredientRepository.findByLibelle(dto.getLibelle());
        if (!existing.isEmpty()) {
            throw new RuntimeException("Un ingrédient avec ce libellé existe déjà.");
        }
        Ingredient ingredient = ingredientMapper.toEntity(dto);
        Ingredient saved = ingredientRepository.save(ingredient);
        return ingredientMapper.toDTO(saved);
    }

    public List<IngredientResponseDto> findAll() {
        List<Ingredient> list = ingredientRepository.findAll();
        return list.stream().map(ingredientMapper::toDTO).toList();
    }

    public IngredientResponseDto findById(Integer id) {
        Ingredient ingr = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrédient introuvable"));
        return ingredientMapper.toDTO(ingr);
    }

    public IngredientResponseDto updateIngredient(Integer id, IngredientRequestDto dto) {
        if (!ingredientRepository.existsById(id)) {
            throw new RuntimeException("Ingrédient introuvable");
        }
        Ingredient updated = ingredientMapper.toEntity(dto);
        updated.setIdIngredient(id);
        Ingredient saved = ingredientRepository.save(updated);
        return ingredientMapper.toDTO(saved);
    }

    public void deleteIngredient(Integer id) {
        if (!ingredientRepository.existsById(id)) {
            throw new RuntimeException("Ingrédient introuvable");
        }
        ingredientRepository.deleteById(id);
    }
}
