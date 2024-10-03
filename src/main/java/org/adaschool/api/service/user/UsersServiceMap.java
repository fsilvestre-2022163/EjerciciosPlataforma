package org.adaschool.api.service.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsersServiceMap implements UsersService {

    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        return userMap.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public List<User> all() {
        return List.copyOf(userMap.values());
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

   /* @Override
    public User update(String userId, UserDto userDto) {
        Optional<User> existingUserOptional = findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.update(userDto); // Actualiza el usuario con el DTO
            userMap.put(userId, existingUser); // Actualiza el usuario en el mapa
            return existingUser;
        }
        throw new UserNotFoundException(userId); // Lanzar excepción si el usuario no se encuentra
    }*/


    @Override
    public User update(String userId, UserDto userDto) {
        Optional<User> existingUserOptional = findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            // Actualiza solo los campos permitidos
            existingUser.update(userDto);
            // Guarda el usuario actualizado en el mapa
            userMap.put(userId, existingUser);
            return existingUser;
        }
        return null; // O lanzar una excepción si no se encuentra el usuario
    }





}