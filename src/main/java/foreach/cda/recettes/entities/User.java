package foreach.cda.recettes.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name="prenom", nullable=false)
    private String prenom;

    @Column(name="role")
    private Boolean role;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="telephone", nullable=false, unique=true, length=10)
    private String telephone;

    @Column(name="mail", nullable=false, unique=true)
    private String mail;

    // table d'association favoris
    @ManyToMany
    @JoinTable(
        name = "favori",
        joinColumns = @JoinColumn(name = "id_user"),
        inverseJoinColumns = @JoinColumn(name = "id_recette")
    )
    private List<Recettes> favoris;


    @OneToMany(mappedBy = "user", targetEntity = Recettes.class, fetch=FetchType.LAZY)
    private List<Recettes> recettes;
}
