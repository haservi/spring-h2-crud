package com.haservi.h2crud.controller;

import com.haservi.h2crud.dto.UserCreateRequest;
import com.haservi.h2crud.dto.UserSearchResponse;
import com.haservi.h2crud.dto.UserUpdateRequest;
import com.haservi.h2crud.entity.User;
import com.haservi.h2crud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody UserCreateRequest request) {
        log.info("save : {}", request.toString());
        User savedUser = userService.save(request.toEntity());
        if (savedUser != null) {
            return ResponseEntity.ok().body("User saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save user");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserSearchResponse>> search() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        try {
            User user = userService.findById(id);
            user.update(request.getName(), request.getAge());
            userService.save(user);
            return ResponseEntity.ok().body("User Update");

        } catch (Exception e) {
            log.error("update error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update Failed");
        }
    }

}
