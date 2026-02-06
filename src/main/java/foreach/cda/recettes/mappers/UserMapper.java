package foreach.cda.recettes.mappers;

import foreach.cda.recettes.dtos.UserDTO;
import foreach.cda.recettes.dtos.UserCreateDTO;
import foreach.cda.recettes.entities.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setRole(user.getRole());
        dto.setTelephone(user.getTelephone());
        dto.setMail(user.getMail());
        return dto;
    }

    public static User toEntity(UserCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());
        user.setTelephone(dto.getTelephone());
        user.setMail(dto.getMail());
        return user;
    }
}