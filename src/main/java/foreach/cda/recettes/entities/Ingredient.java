package foreach.cda.recettes.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient")
    private Integer idIngredient;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    public enum Type {
        boisson,
        aliment
    };

    @Column(name = "nombre_calorique")
    private int nombreCalorique;

    @OneToMany(mappedBy = "ingredient")
    private List<RecetteIngredient> recettes;
}
