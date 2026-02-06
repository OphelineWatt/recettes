package foreach.cda.recettes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foreach.cda.recettes.entities.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    List<Ingredient> findByLibelle(String libelle);

    List<Ingredient> findByType(Ingredient.Type type);
}