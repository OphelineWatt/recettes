package foreach.cda.recettes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foreach.cda.recettes.entities.RecetteIngredient;
import foreach.cda.recettes.entities.Recettes;
import foreach.cda.recettes.entities.Ingredient;

@Repository
public interface RecetteIngredientRepository extends JpaRepository<RecetteIngredient, Integer> {

    List<RecetteIngredient> findByRecette(Recettes recette);

    List<RecetteIngredient> findByIngredient(Ingredient ingredient);

    RecetteIngredient findByRecetteAndIngredient(Recettes recette, Ingredient ingredient);
}