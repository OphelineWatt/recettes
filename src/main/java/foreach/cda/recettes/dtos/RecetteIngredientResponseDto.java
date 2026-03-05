package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecetteIngredientResponseDto {
    private Integer id;
    private Integer recetteId;
    private Integer ingredientId;
    private double quantite;
    private String preparation;
}
