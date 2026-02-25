package foreach.cda.recettes.mappers;

import org.mapstruct.Mapper;

import foreach.cda.recettes.dtos.UserRequestDto;
import foreach.cda.recettes.dtos.UserResponseDto;
import foreach.cda.recettes.entities.User;

@Mapper(componentModel= "spring")
public interface UserMapper {
    
    UserResponseDto toDTO(User user);

    User toEntity(UserRequestDto userRequestDto);
}
