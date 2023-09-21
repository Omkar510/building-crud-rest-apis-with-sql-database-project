package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.service;

import java.util.List;

import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.dto.UserDto;

public interface UserService {
    
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    String deleteUser(Long userId);
}
