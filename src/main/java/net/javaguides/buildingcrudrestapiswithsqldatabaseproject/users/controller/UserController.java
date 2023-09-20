package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    
    private UserService userService;

    // build create User REST API

    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<UserEntity>(userService.createUser(userEntity), HttpStatus.CREATED);
    }


    // build get user by id REST API
    // http://localhost:8080/api/users/1

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long userId) {
        return new ResponseEntity<UserEntity>(userService.getUserById(userId), HttpStatus.OK);
    }

    // build Get All Users REST API
    // http://localhost:8080/api/users

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
    }

    // build Update User REST API
    // http://localhost:8080/api/users/1

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long userId, @RequestBody UserEntity userEntity) {

        userEntity.setId(userId);

        UserEntity updatedUserEntity = userService.updateUser(userEntity);

        return new ResponseEntity<UserEntity>(updatedUserEntity, HttpStatus.OK);
    }

    // build Delete User REST API
    // http://localhost:8080/api/users/1

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<String>(userService.deleteUser(userId), HttpStatus.OK);
    }
}
