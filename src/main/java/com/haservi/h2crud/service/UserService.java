package com.haservi.h2crud.service;

import com.haservi.h2crud.dto.UserSearchResponse;
import com.haservi.h2crud.entity.User;
import com.haservi.h2crud.entity.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<UserSearchResponse> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(UserSearchResponse::of)
                .toList();
    }
}
