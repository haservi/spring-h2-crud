package com.haservi.h2crud.service;

import com.haservi.h2crud.dto.UserSearchResponse;
import com.haservi.h2crud.dto.UserUpdateRequest;
import com.haservi.h2crud.entity.User;
import com.haservi.h2crud.entity.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<UserSearchResponse> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(UserSearchResponse::of)
                .toList();
    }

    @Transactional
    public void updateUser(Long id, UserUpdateRequest request) {
        User user = findById(id);
        user.update(request.getName(), request.getAge());
        saveUser(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
