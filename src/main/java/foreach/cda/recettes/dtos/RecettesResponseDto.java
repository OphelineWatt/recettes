package foreach.cda.recettes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecettesResponseDto {
    private Integer idRecette;
    private String nomPlat;
    private Integer dureePreparation;
    private Integer dureeCuisson;
    private Integer nombreCalorique;
    private Boolean partage;
}
