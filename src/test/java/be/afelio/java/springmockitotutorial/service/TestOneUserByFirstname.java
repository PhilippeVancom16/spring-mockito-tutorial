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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestOneUserByFirstname {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    TestRestTemplate restTemplate;

    ObjectMapper mapper = new ObjectMapper();



    @Test
    public void testFindOneUserByFirstname() throws Exception {

        String firstname = "{\"Philippe\"}";
        String url = "http://localhost:8080/mockito/user/firstname/{firstname}";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, firstname);
        assertEquals(200, response.getStatusCodeValue());

        String json = response.getBody();
        TypeReference<ResponseDto<List<UserDto>>> type = new TypeReference<>() {};
        assertNotNull(json);
        ResponseDto<List<UserDto>> responseDto = mapper.readValue(json, type);
        assertEquals(ResponseDtoStatus.SUCCESS.name(), responseDto.getStatus());

        UserDto userExpected = new UserDto();
        userExpected.setFirstname("Philippe");
        userExpected.setLastname("Vancom");
        userExpected.setCompanyName("Xperthis");

        UserDto userActual = applicationService.findOneUserDtoByFirstname("Philippe");

        assertEquals(userExpected.getFirstname(), userActual.getFirstname());
        assertEquals(userExpected.getLastname(), userActual.getLastname());
        assertEquals(userExpected.getCompanyName(), userActual.getCompanyName());
    }
}
