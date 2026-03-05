package foreach.cda.recettes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import foreach.cda.recettes.dtos.RecetteIngredientRequestDto;
import foreach.cda.recettes.dtos.RecetteIngredientResponseDto;
import foreach.cda.recettes.entities.RecetteIngredient;

@Mapper(componentModel = "spring")
public interface RecetteIngredientMapper {

    @Mapping(source = "recette.idRecette", target = "recetteId")
    @Mapping(source = "ingredient.idIngredient", target = "ingredientId")
    RecetteIngredientResponseDto toDTO(RecetteIngredient entity);

  
    RecetteIngredient toEntity(RecetteIngredientRequestDto dto);
}
