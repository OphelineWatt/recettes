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

    // @OneToMany(mappedBy = "client", targetEntity = Commande.class, fetch=FetchType.LAZY)
    // List<Commande> commandes;
}
