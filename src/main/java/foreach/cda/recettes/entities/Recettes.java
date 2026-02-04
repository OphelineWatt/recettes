package foreach.cda.recettes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recettes")
public class Recettes {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_recette")
    private Integer idRecette;

    @Column(name = "nom_plat", nullable = false)
    private String nomPlat;

    @Column(name="duree_preparation", nullable=false)
    private int dureePreparation;

    @Column(name="nombre_calorique")
    private int nombreCalorique;

    @Column(name = "partage", nullable = false)
    private boolean partage;
    
}

