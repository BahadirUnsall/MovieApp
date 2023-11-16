package com.bahadir.movieapp.controller;

import com.bahadir.movieapp.dto.request.RegisterRequestDto;
import com.bahadir.movieapp.entity.User;
import com.bahadir.movieapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    @PostMapping("/register")
    public ResponseEntity<User> register(RegisterRequestDto dto){
        return ResponseEntity.ok(service.register(dto));
    }


    @GetMapping("/findAllByOrderName")
    public ResponseEntity<List<User>> findAllByOrderByName(){
        return ResponseEntity.ok(service.findAllByOrderByName());
    }

    @GetMapping("/findAllByNameLike")
    public ResponseEntity<List<User>> findAllByNameLike(String name){
        return ResponseEntity.ok(service.findAllByNameLike(name));
    }

    @GetMapping("/findAllByEmailEndsWith")
    public ResponseEntity<List<User>> findAllByEmailEndsWith(String word){
        return ResponseEntity.ok(service.findAllByEmailEndsWith(word));
    }

    @GetMapping("/findByEmailAndPassword")
    public ResponseEntity<User> findByEmailAndPassword(String email, String password){
        return ResponseEntity.of(service.findByEmailAndPassword(email,password));
    }

    @GetMapping("/existsByEmailAndPassword")
    public ResponseEntity<Boolean> existsByEmailAndPassword(String email, String password){
        return ResponseEntity.ok(service.existsByEmailAndPassword(email,password));
    }

    @GetMapping("/passwordLongerThanNativeQuery")
    public ResponseEntity<List<User>> passwordLongerThanNativeQuery(Integer value){
        return ResponseEntity.ok(service.passwordLongerThanNativeQuery(value));
    }

    @GetMapping("/passwordLongerThanJPQL")
    public ResponseEntity<List<User>> passwordLongerThanJPQL(Integer value){
        return ResponseEntity.ok(service.passwordLongerThanJPQL(value));
    }

}
