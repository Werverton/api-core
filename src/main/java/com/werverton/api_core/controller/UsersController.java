package com.werverton.api_core.controller;

import com.werverton.api_core.api.UsersApi;
import com.werverton.api_core.model.UserRequest;
import com.werverton.api_core.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UsersController implements UsersApi {

    private Map<String, UserResponse> users= new HashMap<>();


    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        var user = toUserResponse(userRequest);
        users.put(user.getId(), user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        UserResponse user = users.remove(id);
        return user != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> allUsers = this.users.values()
                .stream()
                .toList();
        return ResponseEntity.ok(allUsers);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        var user = users.get(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(String id, UserRequest userRequest) {
        var userById = users.get(id);

        if (userById != null) {
            var newUser = toUserResponse(userRequest);
            newUser.setId(userById.getId());
            newUser.setCreatedAt(userById.getCreatedAt());
            users.put(id, newUser);
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.notFound().build();
    }

    private UserResponse toUserResponse(UserRequest userRequest) {
        return new UserResponse()
                .id(UUID.randomUUID().toString())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .createdAt(OffsetDateTime.now())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .status(userRequest.getStatus())
                .password(userRequest.getPassword());
    }
}
