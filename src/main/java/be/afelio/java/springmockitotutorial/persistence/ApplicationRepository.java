package be.afelio.java.springmockitotutorial.persistence;

import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.entity.UserEntity;
import be.afelio.java.springmockitotutorial.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRepository {

    private final UserRepository userRepository;

    public ApplicationRepository(UserRepository userRepository) {   // = @Autowired ApplicationRepository repository
        this.userRepository = userRepository;
    }



    private UserDto createUserDto(UserEntity entity) {
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

    public List<UserDto> findAllUserDto() {
        List<UserEntity> userList = userRepository.findAll();
        List<UserDto> listDto = new ArrayList<>();

        for (UserEntity user : userList) {
            listDto.add(createUserDto(user));
        }
        return listDto;
    }
}
