package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.exception.EmailAlreadyExistsException;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.exception.ResourceNotFoundException;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.dto.UserDto;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.mapper.AutoUserMapper;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.repository.UserRepository;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    // private ModelMapper modelMapper;

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto Into User JPA Entity

        // UserEntity userEntity = UserMapper.mapToUserEntity(userDto);

        // UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        if (userRepository.existsByEmail(userDto.getEmailAddress()))
            throw new EmailAlreadyExistsException("Email Already Exists for User");

        UserEntity userEntity = AutoUserMapper.MAPPER.mapToUser(userDto);

        userEntity = userRepository.save(userEntity);

        // Convert User JPA Entity Into UserDto

        // userDto = UserMapper.mapToUserDto(userEntity);

        // userDto = modelMapper.map(userEntity, UserDto.class);

        userDto = AutoUserMapper.MAPPER.mapToUserDto(userEntity);

        return userDto;
    }

    @Override
    public UserDto getUserById(Long userId) {

        // UserDto userDto = UserMapper.mapToUserDto(
        // userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User
        // not found....")));

        // UserDto userDto = modelMapper.map(
        // userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User
        // not found....")),
        // UserDto.class);

        UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(
                userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId, "USER_NOT_FOUND")));

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserEntity> userEntities = userRepository.findAll();

        // return
        // userEntities.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

        // return userEntities.stream().map(userEntity -> modelMapper.map(userEntity,
        // UserDto.class))
        // .collect(Collectors.toList());

        return userEntities.stream().map(userEntity -> AutoUserMapper.MAPPER.mapToUserDto(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        UserEntity existingUserEntity = userRepository.findById(userDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userDto.getUserId(), "USER_NOT_FOUND"));
        existingUserEntity.setFirstName(userDto.getFirstName());
        existingUserEntity.setLastName(userDto.getLastName());
        existingUserEntity.setEmail(userDto.getEmailAddress());

        existingUserEntity = userRepository.save(existingUserEntity);

        // return UserMapper.mapToUserDto(existingUserEntity);

        // return modelMapper.map(existingUserEntity, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(existingUserEntity);
    }

    @Override
    public String deleteUser(Long userId) {

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId, "USER_NOT_FOUND"));

        userRepository.delete(userEntity);

        return "User Deleted Successfully";
    }

}
