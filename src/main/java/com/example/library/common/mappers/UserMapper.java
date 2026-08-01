package com.example.library.common.mappers;

import com.example.library.dto.request.user.RegisterUserRequest;
import com.example.library.dto.response.UserResponse;
import com.example.library.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class UserMapper {

    public User toUser(RegisterUserRequest registerUserRequest) {
        return User.builder()
                .documentNumber(registerUserRequest.documentNumber())
                .firstName(registerUserRequest.firstName())
                .lastName(registerUserRequest.lastName())
                .email(registerUserRequest.email())
                .birthDate(registerUserRequest.birthDate())
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .documentNumber(user.getDocumentNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(LocalDate.parse(String.valueOf(user.getBirthDate())))
                .build();
    }


}
