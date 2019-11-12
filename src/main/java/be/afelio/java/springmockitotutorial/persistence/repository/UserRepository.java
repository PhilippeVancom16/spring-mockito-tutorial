package be.afelio.java.springmockitotutorial.persistence.repository;

import be.afelio.java.springmockitotutorial.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findAll();

    UserEntity findOneByFirstnameIgnoreCase(String firstname);
}
