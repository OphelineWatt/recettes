package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecetteIngredientRequestDto {
    private Integer recetteId;
    private Integer ingredientId;
    private double quantite;
    private String preparation;
}
