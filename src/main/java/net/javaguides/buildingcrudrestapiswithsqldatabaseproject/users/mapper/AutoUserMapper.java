package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.dto.UserDto;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;

@Mapper
public interface AutoUserMapper {
    
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "email", target = "emailAddress")
    UserDto mapToUserDto(UserEntity userEntity);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "emailAddress", target = "email")
    UserEntity mapToUser(UserDto userDto);
}
