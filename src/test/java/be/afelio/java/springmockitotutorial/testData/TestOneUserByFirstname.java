package be.afelio.java.springmockitotutorial.testData;

import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.ApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestOneUserByFirstname {

    @Autowired
    ApplicationRepository repository;

    @Test
    public void testFindOneUserByFirstname() {

        UserDto userExpected = new UserDto();
        userExpected.setFirstname("Philippe");
        userExpected.setLastname("Vancom");
        userExpected.setCompanyName("Xperthis");

        UserDto userActual = repository.findOneUserDtoByFirstname("Philippe");

        assertEquals(userExpected, userActual);
    }
}
