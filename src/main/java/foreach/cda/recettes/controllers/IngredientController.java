package foreach.cda.recettes.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import foreach.cda.recettes.dtos.IngredientRequestDto;
import foreach.cda.recettes.dtos.IngredientResponseDto;
import foreach.cda.recettes.services.IngredientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public IngredientResponseDto create(@RequestBody IngredientRequestDto dto) {
        return ingredientService.createIngredient(dto);
    }

    @GetMapping
    public List<IngredientResponseDto> findAll() {
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public IngredientResponseDto findById(@PathVariable Integer id) {
        return ingredientService.findById(id);
    }

    @PutMapping("/{id}")
    public IngredientResponseDto update(@PathVariable Integer id, @RequestBody IngredientRequestDto dto) {
        return ingredientService.updateIngredient(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ingredientService.deleteIngredient(id);
    }
}
