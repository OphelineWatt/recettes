package foreach.cda.recettes.mappers;

import org.mapstruct.Mapper;

import foreach.cda.recettes.dtos.RecettesRequestDto;
import foreach.cda.recettes.dtos.RecettesResponseDto;
import foreach.cda.recettes.entities.Recettes;

@Mapper(componentModel= "spring")
public interface RecettesMapper {
    
    RecettesResponseDto toDTO(Recettes recettes);

    Recettes toEntity(RecettesRequestDto recettesRequestDto);
}
