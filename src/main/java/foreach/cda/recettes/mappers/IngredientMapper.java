package foreach.cda.recettes.mappers;

import org.mapstruct.Mapper;

import foreach.cda.recettes.dtos.IngredientRequestDto;
import foreach.cda.recettes.dtos.IngredientResponseDto;
import foreach.cda.recettes.entities.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientResponseDto toDTO(Ingredient ingredient);

    Ingredient toEntity(IngredientRequestDto dto);
}
