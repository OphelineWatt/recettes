package foreach.cda.recettes.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import foreach.cda.recettes.dtos.RecetteIngredientRequestDto;
import foreach.cda.recettes.dtos.RecetteIngredientResponseDto;
import foreach.cda.recettes.services.RecetteIngredientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recette-ingredients")
@RequiredArgsConstructor
public class RecetteIngredientController {

    private final RecetteIngredientService riService;

    @PostMapping
    public RecetteIngredientResponseDto create(@RequestBody RecetteIngredientRequestDto dto) {
        return riService.create(dto);
    }

    @GetMapping
    public List<RecetteIngredientResponseDto> findAll() {
        return riService.findAll();
    }

    @GetMapping("/{id}")
    public RecetteIngredientResponseDto findById(@PathVariable Integer id) {
        return riService.findById(id);
    }

    @PutMapping("/{id}")
    public RecetteIngredientResponseDto update(@PathVariable Integer id, @RequestBody RecetteIngredientRequestDto dto) {
        return riService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        riService.delete(id);
    }

    // optional query endpoints
    @GetMapping(params = "recetteId")
    public List<RecetteIngredientResponseDto> findByRecette(@RequestParam Integer recetteId) {
        return riService.findByRecette(recetteId);
    }

    @GetMapping(params = "ingredientId")
    public List<RecetteIngredientResponseDto> findByIngredient(@RequestParam Integer ingredientId) {
        return riService.findByIngredient(ingredientId);
    }
}
