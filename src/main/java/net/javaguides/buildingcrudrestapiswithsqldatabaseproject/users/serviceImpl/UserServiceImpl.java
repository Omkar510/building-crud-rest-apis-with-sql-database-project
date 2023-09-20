package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.repository.UserRepository;
import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found...."));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {

        UserEntity existingUserEntity = userRepository.findById(userEntity.getId()).get();

        existingUserEntity.setFirstName(userEntity.getFirstName());
        existingUserEntity.setLastName(userEntity.getLastName());
        existingUserEntity.setEmail(userEntity.getEmail());

        return userRepository.save(existingUserEntity);
    }

    @Override
    public String deleteUser(Long userId) {

        userRepository.deleteById(userId);

        return "User Deleted Successfully";
    }

}
