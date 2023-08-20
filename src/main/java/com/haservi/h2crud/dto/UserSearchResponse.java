package com.haservi.h2crud.dto;

import com.haservi.h2crud.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSearchResponse {

    private long id;

    private String userId;

    private String name;

    private int age;


    @Builder
    public UserSearchResponse(long id, String userId, String name, int age) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    public static UserSearchResponse of(User user) {
        return UserSearchResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }

    @Override
    public String toString() {
        return "UserSearchResponse{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
