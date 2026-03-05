package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientRequestDto {
    private String libelle;
    private foreach.cda.recettes.entities.Ingredient.Type type;
    private int nombreCalorique;
}
