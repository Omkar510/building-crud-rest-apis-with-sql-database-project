package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.dto.UserDto;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.mapper.UserMapper;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.repository.UserRepository;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto Into User JPA Entity

        UserEntity userEntity = UserMapper.mapToUserEntity(userDto);

        userEntity = userRepository.save(userEntity);

        // Convert User JPA Entity Into UserDto

        userDto = UserMapper.mapToUserDto(userEntity);

        return userDto;
    }

    @Override
    public UserDto getUserById(Long userId) {

        UserDto userDto = UserMapper.mapToUserDto(
                userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found....")));

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities.stream().map(data -> UserMapper.mapToUserDto(data)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        UserEntity existingUserEntity = userRepository.findById(userDto.getId()).get();

        existingUserEntity.setFirstName(userDto.getFirstName());
        existingUserEntity.setLastName(userDto.getLastName());
        existingUserEntity.setEmail(userDto.getEmail());

        existingUserEntity = userRepository.save(existingUserEntity);

        return UserMapper.mapToUserDto(existingUserEntity);
    }

    @Override
    public String deleteUser(Long userId) {

        userRepository.deleteById(userId);

        return "User Deleted Successfully";
    }

}
