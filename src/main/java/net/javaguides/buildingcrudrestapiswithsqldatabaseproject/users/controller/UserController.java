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
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.dto.UserDto;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    
    private UserService userService;

    // build create User REST API

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
    }


    // build get user by id REST API
    // http://localhost:8080/api/users/1

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
    }

    // build Get All Users REST API
    // http://localhost:8080/api/users

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    // build Update User REST API
    // http://localhost:8080/api/users/1

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto) {

        userDto.setId(userId);

        UserDto updatedUserDto = userService.updateUser(userDto);

        return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);
    }

    // build Delete User REST API
    // http://localhost:8080/api/users/1

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<String>(userService.deleteUser(userId), HttpStatus.OK);
    }
}
