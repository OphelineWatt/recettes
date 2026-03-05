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

import foreach.cda.recettes.dtos.RecettesResponseDto;
import foreach.cda.recettes.services.RecettesService;

import foreach.cda.recettes.dtos.RecettesRequestDto;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recettes")
@RequiredArgsConstructor
public class RecettesController {

    private final RecettesService recettesService;

    @PostMapping
    public RecettesResponseDto create(@RequestParam Integer userId, @RequestBody RecettesRequestDto dto) {
        return recettesService.createRecettes(dto, userId);
    }

@GetMapping
public List<RecettesResponseDto> findAll() {
    return recettesService.findAll();
}

@GetMapping("/{id}")
public RecettesResponseDto findById(@PathVariable Integer id) {
    return recettesService.findById(id);
}

@PutMapping("/{id}")
public RecettesResponseDto update(@PathVariable Integer id,
        @RequestParam Integer userId,
        @RequestBody RecettesRequestDto dto) {
    return recettesService.updateRecettes(id, userId, dto);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Integer id) {
    recettesService.deleteRecette(id);
}

}
