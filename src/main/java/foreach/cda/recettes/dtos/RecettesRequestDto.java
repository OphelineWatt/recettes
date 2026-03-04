package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecettesRequestDto {
    private String nomPlat;
    private Integer dureePreparation;
    private Integer dureeCuisson;
    private Integer nombreCalorique;
    private Boolean partage;

}
