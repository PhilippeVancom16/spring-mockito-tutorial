package be.afelio.java.springmockitotutorial.testAllUser;

import be.afelio.java.springmockitotutorial.api.dto.ResponseDto;
import be.afelio.java.springmockitotutorial.api.dto.ResponseDtoStatus;
import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.ApplicationRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class MockitoTest {

    @Autowired
    ApplicationRepository repository;

    @Autowired
    TestRestTemplate restTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testAllUser() throws Exception{
        ResponseEntity<String> response = restTemplate.getForEntity("/user/all", String.class);
        assertEquals(200, response.getStatusCodeValue());

        String json = response.getBody();

        TypeReference<ResponseDto<List<UserDto>>> type = new TypeReference<>() {};
        assert json != null;
        ResponseDto<List<UserDto>> responseDto = mapper.readValue(json, type);

        assertEquals(ResponseDtoStatus.SUCCESS.name(), responseDto.getStatus());

        List<UserDto> expected = createListUser();
        List<UserDto> actual = repository.findAllUserDto();
        assertEquals(expected, actual);
    }

    private List<UserDto> createListUser() {
        UserDto user1 = new UserDto("Louis", "Hella", "Xperthis");
        UserDto user2 = new UserDto("Philippe", "Vancom", "Xperthis");
        UserDto user3 = new UserDto("Romain", "Gerardy", "Afelio");
        UserDto user4 = new UserDto("Jean", "VanRickstal", "NRB");
        UserDto user5 = new UserDto("Benoit", "Dormaels", "Afelio");
        List<UserDto> listUser = new ArrayList<>();
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
        listUser.add(user4);
        listUser.add(user5);
        return listUser;
    }
}
