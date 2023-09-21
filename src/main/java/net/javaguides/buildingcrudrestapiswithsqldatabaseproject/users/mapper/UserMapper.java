package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.mapper;

import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.dto.UserDto;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;

public class UserMapper {
    
    // Convert User JPA Entity Into UserDto

    public static UserDto mapToUserDto(UserEntity userEntity) {

        UserDto userDto = new UserDto(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(),
        userEntity.getEmail());

        return userDto;

    }

    // Convert UserDto Into User JPA Entity

    public static UserEntity mapToUserEntity(UserDto userDto) {

        UserEntity userEntity = new UserEntity(userDto.getId(), userDto.getFirstName(), userDto.getLastName(),
                userDto.getEmail());

        return userEntity;
    }
}
