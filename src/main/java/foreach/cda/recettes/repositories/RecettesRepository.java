package foreach.cda.recettes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foreach.cda.recettes.entities.Recettes;
import foreach.cda.recettes.entities.User;

@Repository
public interface RecettesRepository extends JpaRepository<Recettes, Integer> {

    List<Recettes> findByPartage(boolean partage);

    List<Recettes> findByUser(User user);

    List<Recettes> findByNomPlat(String nomPlat);
}