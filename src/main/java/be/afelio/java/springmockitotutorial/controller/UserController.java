package be.afelio.java.springmockitotutorial.controller;

import be.afelio.java.springmockitotutorial.api.dto.ResponseDto;
import be.afelio.java.springmockitotutorial.api.dto.ResponseDtoStatus;
import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.ApplicationRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    private final ApplicationRepository repository;

    public UserController(ApplicationRepository repository) {   // = @Autowired UserRepository userRepository
        this.repository = repository;
    }



    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<UserDto>>> findAllUser() {

        ResponseDto<List<UserDto>> dto = null;

        try {
            List<UserDto> user = repository.findAllUserDto();

            if (user == null) {
                dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "No user found");
            } else {
                dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, user.size() + " users found");
                dto.setPayload(user);
            }

        } catch (Exception e) {
            dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Unexpected exception");
        }
        return ResponseEntity.ok(dto);
    }
}
