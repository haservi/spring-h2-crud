package com.haservi.h2crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @Schema(description = "이름", example = "홍길은")
    private String name;

    @Schema(description = "나이", example = "20")
    private int age;

}
