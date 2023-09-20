package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
