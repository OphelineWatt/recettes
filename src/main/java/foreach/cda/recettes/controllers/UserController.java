package foreach.cda.recettes.controllers;

import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foreach.cda.recettes.dtos.RecetteIngredientResponseDto;
import foreach.cda.recettes.dtos.RecettesRequestDto;
import foreach.cda.recettes.dtos.RecettesResponseDto;
import foreach.cda.recettes.dtos.UserRequestDto;
import foreach.cda.recettes.dtos.UserResponseDto;
import foreach.cda.recettes.services.PdfService;
import foreach.cda.recettes.services.RecetteIngredientService;
import foreach.cda.recettes.services.RecettesService;
import foreach.cda.recettes.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RecettesService recettesService;
    private final PdfService pdfService;
    private final RecetteIngredientService ingredientRecetteService;

    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto dto) {
        return userService.createUser(dto);
    }

    @PostMapping("/login")
    public foreach.cda.recettes.dtos.UserLoginResponseDto login(
            @RequestBody foreach.cda.recettes.dtos.UserLoginRequestDto dto) {
        return userService.login(dto);
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin()) {
            throw new RuntimeException("Accès refusé");
        }
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Integer id) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !id.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Integer id, @RequestBody UserRequestDto dto) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !id.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !id.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        userService.deleteUser(id);
    }

    // Gestion des recettes relié au client
    @PostMapping("/{userId}/recettes")
    public RecettesResponseDto createRecette(@PathVariable Integer userId, @RequestBody RecettesRequestDto dto) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !userId.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        return recettesService.createRecettes(dto, userId);
    }

    @PutMapping("/{userId}/recettes/{idRecette}")
    public RecettesResponseDto updateRecette(@PathVariable Integer userId, @PathVariable Integer idRecette,
            @RequestBody RecettesRequestDto dto) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !userId.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        return recettesService.updateRecettes(idRecette, userId, dto);
    }

    @DeleteMapping("/{userId}/recettes/{idRecette}")
    public void deleteRecette(@PathVariable Integer userId, @PathVariable Integer idRecette) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !userId.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        recettesService.deleteRecette(idRecette);
    }

    // gestion des favoris
    @PostMapping("/{userId}/favoris/{recetteId}")
    public UserResponseDto addFavori(@PathVariable Integer userId, @PathVariable Integer recetteId) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !userId.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        return userService.addFavorite(userId, recetteId);
    }

    @DeleteMapping("/{userId}/favoris/{recetteId}")
    public UserResponseDto removeFavori(@PathVariable Integer userId, @PathVariable Integer recetteId) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !userId.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        return userService.removeFavorite(userId, recetteId);
    }

    @GetMapping("/{userId}/favoris")
    public java.util.List<RecettesResponseDto> listFavoris(@PathVariable Integer userId) {
        Integer current = foreach.cda.recettes.config.SecurityUtil.getCurrentUserId();
        if (!foreach.cda.recettes.config.SecurityUtil.isAdmin() && !userId.equals(current)) {
            throw new RuntimeException("Accès refusé");
        }
        return userService.getFavorites(userId);
    }

    @GetMapping("/{userId}/recettes/{recetteId}/pdf")
    public ResponseEntity<byte[]> getPdf(@PathVariable Integer userId,
            @PathVariable Integer recetteId) {

        RecettesResponseDto recette = recettesService.findById(recetteId);
        List<RecetteIngredientResponseDto> ingredients = ingredientRecetteService.findByRecette(recetteId);

        try {
            byte[] pdf = pdfService.generateRecettesPdf(recette, ingredients);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recette.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur lors de la génération du PDF");
        }
    }

}
