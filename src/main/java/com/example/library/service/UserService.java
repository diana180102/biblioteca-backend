package com.example.library.service;

import com.example.library.dto.request.user.RegisterUserRequest;
import com.example.library.dto.request.user.UpdateUserRequest;
import com.example.library.dto.response.UserResponse;
import com.example.library.entity.User;
import com.example.library.exception.ConflictException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.common.mappers.UserMapper;
import com.example.library.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(RegisterUserRequest registerUserRequest) {


        if (userRepository.existsByEmail(registerUserRequest.email())) {
            throw new ConflictException("El correo electrónico ya está registrado.");
        }

        if (userRepository.existsByDocumentNumber(registerUserRequest.documentNumber())) {
            throw new ConflictException("El número de documento ya está registrado.");
        }

        User user = userMapper.toUser(registerUserRequest);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UpdateUserRequest updateUserRequest, UUID id) {

        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No existe el usuario"));

        Optional.ofNullable(updateUserRequest.documentNumber()).ifPresent(existingUser::setDocumentNumber);
        Optional.ofNullable(updateUserRequest.email()).ifPresent(existingUser::setEmail);
        Optional.ofNullable(updateUserRequest.firstName()).ifPresent(existingUser::setFirstName);
        Optional.ofNullable(updateUserRequest.lastName()).ifPresent(existingUser::setLastName);
        Optional.ofNullable(updateUserRequest.birthDate()).ifPresent(existingUser::setBirthDate);
    }

    @Transactional
    public void deleteUser(UUID id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No existe el usuario"));

        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UserResponse findUserByDocument(String documentNumber) {
        User user = userRepository.findByDocumentNumber(documentNumber);
        return userMapper.toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No existe el usuario")
        );
        return userMapper.toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> findAllUsers(int page, int size, String documentNumber) {

        Pageable pageable = PageRequest.of(page, size);

        if (documentNumber != null && !documentNumber.isBlank()) {
            return userRepository.findByDocumentNumberContaining(documentNumber, pageable)
                    .map(userMapper::toUserResponse);
        }

        return userRepository
                .findAll(pageable)
                .map(userMapper::toUserResponse);
    }

}
