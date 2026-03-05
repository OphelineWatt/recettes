package foreach.cda.recettes.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recettes")
public class Recettes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recette")
    private Integer idRecette;

    @Column(name = "nom_plat", nullable = false)
    private String nomPlat;

    @Column(name = "duree_preparation", nullable = false)
    private int dureePreparation;

    @Column(name = "duree_cuisson", nullable = false)
    private int dureeCuisson;

    @Column(name = "nombre_calorique")
    private int nombreCalorique;

    @Column(name = "partage", nullable = false)
    private boolean partage;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user ;

    //cascade = CascadeType.ALL pour que les ingrédients soient supprimés si la recette est supprimée
    @OneToMany(mappedBy = "recette", cascade = CascadeType.ALL)
    private List<RecetteIngredient> ingredients;

    // utilisateurs ayant mis cette recette en favoris (relation inverse)
    @ManyToMany(mappedBy = "favoris")
    private List<User> usersFavoris;

}
