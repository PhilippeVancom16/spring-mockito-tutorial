package be.afelio.java.springmockitotutorial.service.mapper;

import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapUserDto(UserEntity entity) {
        UserDto user = null;
        if (entity != null) {
            user = new UserDto(
                    entity.getFirstname(),
                    entity.getLastname(),
                    entity.getCompany().getName()
            );
        }
        return user;
    }
}
