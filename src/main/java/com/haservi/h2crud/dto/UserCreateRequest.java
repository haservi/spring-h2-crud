package com.haservi.h2crud.dto;

import com.haservi.h2crud.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "사용자 생성 요청")
public class UserCreateRequest {

    @Schema(description = "사용자 ID", example = "user")
    private String userId;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "나이", example = "15")
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
        return "UserCreateRequest{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
