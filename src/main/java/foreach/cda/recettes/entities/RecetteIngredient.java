package foreach.cda.recettes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recette_ingredient")
@Getter
@Setter

public class RecetteIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recette_ingredient")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_recette", nullable = false)
    private Recettes recette;

    @ManyToOne
    @JoinColumn(name = "id_ingredient", nullable = false)
    private Ingredient ingredient;

    @Column(nullable = false)
    private double quantite;

    @Column(length = 255)
    private String preparation;
    
}
