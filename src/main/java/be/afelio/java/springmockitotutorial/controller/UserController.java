package be.afelio.java.springmockitotutorial.controller;

import be.afelio.java.springmockitotutorial.api.dto.ResponseDto;
import be.afelio.java.springmockitotutorial.api.dto.ResponseDtoStatus;
import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.service.ApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    private final ApplicationService applicationService;

    public UserController(ApplicationService applicationService) {   // = @Autowired UserRepository userRepository
        this.applicationService = applicationService;
    }



    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<UserDto>>> findAllUser() {

        ResponseDto<List<UserDto>> dto = null;

        try {
            List<UserDto> user = applicationService.findAllUserDto();

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

    @GetMapping(value = "firstname/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<UserDto>> findOneUserByFirstname(
            @PathVariable("firstname") String firstname) {

        ResponseDto<UserDto> dto = null;

        try {
            UserDto user = applicationService.findOneUserDtoByFirstname(firstname);

            if (user == null) {
                dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "No user found");
            } else {
                dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, "User found");
                dto.setPayload(user);
            }

        } catch (Exception e) {
            dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Unexpected exception");
        }
        return ResponseEntity.ok(dto);
    }
}
