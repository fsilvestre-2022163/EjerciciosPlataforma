package org.adaschool.api.controller.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        User createdUser = usersService.save(user);
        URI createdUserUri = URI.create("/v1/users/" + createdUser.getId());
        return ResponseEntity.created(createdUserUri).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        return usersService.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new UserNotFoundException(id)); // Pasar el ID completo
    }

    /*@PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        User updatedUser = usersService.update(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        Optional<User> existingUser = usersService.findById(id);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(id); // Lanza la excepción si el usuario no existe.
        }
        User userToUpdate = existingUser.get();
        userToUpdate.setName(userDto.getName());
        userToUpdate.setLastName(userDto.getLastName());
        usersService.save(userToUpdate);
        return ResponseEntity.ok().build();
    }









    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        usersService.findById(id).orElseThrow(() -> new UserNotFoundException(id)); // Pasar el ID completo
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}