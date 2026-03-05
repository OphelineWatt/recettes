package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDto {
    private String token;
    private Integer idUser;
    private Boolean role;
    private String mail;
}
