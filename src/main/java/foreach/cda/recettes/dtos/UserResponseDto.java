package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Integer idUser;
    private String nom;
    private String prenom;
    private Boolean role;
    private String telephone;
    private String mail;

    // récettes en favoris – on retourne les objets complets
    private java.util.List<RecettesResponseDto> favoris;
}
