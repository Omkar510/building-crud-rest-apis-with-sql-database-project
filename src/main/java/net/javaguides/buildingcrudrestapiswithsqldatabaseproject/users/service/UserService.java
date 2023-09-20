package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.service;

import java.util.List;

import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;

public interface UserService {
    
    UserEntity createUser(UserEntity userEntity);

    UserEntity getUserById(Long userId);

    List<UserEntity> getAllUsers();

    UserEntity updateUser(UserEntity userEntity);

    String deleteUser(Long userId);
}
