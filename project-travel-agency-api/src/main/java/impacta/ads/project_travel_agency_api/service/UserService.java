package impacta.ads.project_travel_agency_api.service;


import impacta.ads.project_travel_agency_api.controller.CreateUserDto;
import impacta.ads.project_travel_agency_api.controller.UpdateUserDto;
import impacta.ads.project_travel_agency_api.entity.User;
import impacta.ads.project_travel_agency_api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        // DTO -> ENTITY
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDto) {

        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }

    }

    public void deleteById(String userId) {

        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }
}