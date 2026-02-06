package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    private String nom;
    private String prenom;
    private Boolean role;
    private String password;
    private String telephone;
    private String mail;
}