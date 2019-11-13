package be.afelio.java.springmockitotutorial.service;

import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.entity.UserEntity;
import be.afelio.java.springmockitotutorial.persistence.repository.UserRepository;
import be.afelio.java.springmockitotutorial.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ApplicationService(UserRepository userRepository, UserMapper userMapper) {   // = @Autowired ApplicationRepository repository
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    public List<UserDto> findAllUserDto() {
        List<UserEntity> userList = userRepository.findAll();
        List<UserDto> listDto = new ArrayList<>();

        for (UserEntity user : userList) {
            listDto.add(userMapper.mapUserDto(user));
        }
        return listDto;
    }

    public UserDto findOneUserDtoByFirstname(String firstname) {

        UserEntity user = userRepository.findOneByFirstnameIgnoreCase(firstname);

        return userMapper.mapUserDto(user);
    }
}
