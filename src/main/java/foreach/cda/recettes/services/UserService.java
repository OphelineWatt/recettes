package foreach.cda.recettes.services;
import org.springframework.stereotype.Service;

import foreach.cda.recettes.mappers.UserMapper;
import foreach.cda.recettes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

}
