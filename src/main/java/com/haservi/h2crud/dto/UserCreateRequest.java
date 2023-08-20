package com.haservi.h2crud.dto;

import com.haservi.h2crud.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateRequest {

    private String userId;

    private String name;

    private int age;

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .name(this.name)
                .age(this.age)
                .build();
    }

    @Override
    public String toString() {
        return "UserCreateRequestDto{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
