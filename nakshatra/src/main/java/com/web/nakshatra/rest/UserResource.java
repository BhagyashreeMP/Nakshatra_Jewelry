package com.web.nakshatra.rest;

import com.web.nakshatra.model.User;
import com.web.nakshatra.repository.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/User")
public class UserResource {

    private final UserRepo UserRepository;

    public UserResource(UserRepo UserRepository) {
        this.UserRepository = UserRepository;
    }

    // Create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User User) {
        return ResponseEntity.ok(UserRepository.save(User));
    }

    // Get all User items
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(UserRepository.findAll());
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> User = UserRepository.findById(id);
        return User.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return UserRepository.findById(id)
                .map(existingUser -> {
                    updatedUser.setUserId(existingUser.getUserId());
                    return ResponseEntity.ok(UserRepository.save(updatedUser));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (UserRepository.existsById(id)) {
            UserRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
