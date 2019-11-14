package be.afelio.java.springmockitotutorial.service;

import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestOneUserByFirstname {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    TestRestTemplate restTemplate;



    @Test
    public void testFindOneUserByFirstname() {

        String firstname = "{\"Philippe\"}";
        String url = "http://localhost:8080/mockito/user/firstname/{firstname}";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, firstname);
        assertEquals(200, response.getStatusCodeValue());

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
