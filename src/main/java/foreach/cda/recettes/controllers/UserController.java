package foreach.cda.recettes.controllers;

import java.util.List;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import foreach.cda.recettes.dtos.UserRequestDto;
import foreach.cda.recettes.dtos.UserResponseDto;
import foreach.cda.recettes.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
@PostMapping
public UserResponseDto create(@RequestBody UserRequestDto dto) {
    return userService.createUser(dto);
}

@GetMapping
public List<UserResponseDto> findAll() {
    return userService.findAll();
}

@GetMapping("/{id}")
public UserResponseDto findById(@PathVariable Integer id) {
    return userService.findById(id);
}

@PutMapping("/{id}")
public UserResponseDto update(@PathVariable Integer id, @RequestBody UserRequestDto dto) {
    return userService.updateUser(id, dto);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Integer id) {
    userService.deleteUser(id);
}
}
