package foreach.cda.recettes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foreach.cda.recettes.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByMail (String mail);

    
}
