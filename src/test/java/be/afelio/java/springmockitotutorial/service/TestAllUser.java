package be.afelio.java.springmockitotutorial.service;

import be.afelio.java.springmockitotutorial.api.dto.ResponseDto;
import be.afelio.java.springmockitotutorial.api.dto.ResponseDtoStatus;
import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestAllUser {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    TestRestTemplate restTemplate;

    ObjectMapper mapper = new ObjectMapper();



    @Test
    public void testAllUser() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/all", String.class);
        assertEquals(200, response.getStatusCodeValue());

        String json = response.getBody();
        TypeReference<ResponseDto<List<UserDto>>> type = new TypeReference<>() {};
        assertNotNull(json);
        ResponseDto<List<UserDto>> responseDto = mapper.readValue(json, type);
        assertEquals(ResponseDtoStatus.SUCCESS.name(), responseDto.getStatus());

        List<UserDto> expected = createListUser();
        List<UserDto> actual = applicationService.findAllUserDto();
        assertEquals(expected, actual);
    }

    public List<UserDto> createListUser() {
        List<UserDto> listUser = new ArrayList<>();
        listUser.add(new UserDto("Louis", "Hella", "Xperthis"));
        listUser.add(new UserDto("Philippe", "Vancom", "Xperthis"));
        listUser.add(new UserDto("Romain", "Gerardy", "Afelio"));
        listUser.add(new UserDto("Jean", "VanRickstal", "NRB"));
        listUser.add(new UserDto("Benoit", "Dormaels", "Afelio"));
        return listUser;
    }
}
