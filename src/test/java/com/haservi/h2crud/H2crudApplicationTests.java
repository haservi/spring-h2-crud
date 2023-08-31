package com.haservi.h2crud;

import com.haservi.h2crud.dto.UserSearchResponse;
import com.haservi.h2crud.entity.User;
import com.haservi.h2crud.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class H2crudApplicationTests {

    @Autowired
    private UserService userService;

    @BeforeEach
    void init() {
        createUser();
    }

    private void createUser() {
        userService.saveUser(
                User.builder()
                        .userId("test")
                        .name("이름")
                        .age(20)
                        .build()
        );
    }

    @Test
    void user_check() {
        List<UserSearchResponse> userSearchResponseList = userService.findAll();
        Assertions.assertThat(userSearchResponseList.get(0).getUserId()).isEqualTo("test");
    }

    @Test
    void user_list_check() {
        createUser();
        List<UserSearchResponse> userSearchResponseList = userService.findAll();
        Assertions.assertThat(userSearchResponseList).hasSize(2);
    }

}
