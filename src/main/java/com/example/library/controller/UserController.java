package com.example.library.controller;

import com.example.library.dto.request.user.RegisterUserRequest;

import com.example.library.dto.request.user.UpdateUserRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.UserResponse;

import com.example.library.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        userService.createUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Usuario creado exitosamente", null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest, @PathVariable UUID id) {
        userService.updateUser(updateUserRequest,  id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Usuario actualizado", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Usuario eliminado", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable UUID id) {
        UserResponse user = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Usuario", user ));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @RequestParam
            @Pattern(regexp = "\\d+", message = "El documento solo debe contener números")
            String documentNumber) {

        UserResponse user = userService.findUserByDocument(documentNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Usuario", user ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String documentNumber) {

        Page<UserResponse> users = userService.findAllUsers(page, size, documentNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Listado de Usuarios", users ));
    }

}
