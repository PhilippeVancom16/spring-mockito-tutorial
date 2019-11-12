package be.afelio.java.springmockitotutorial.testController;

import be.afelio.java.springmockitotutorial.api.dto.ResponseDto;
import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.controller.UserController;
import be.afelio.java.springmockitotutorial.persistence.ApplicationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TestController {

    @Mock
    ApplicationRepository repository;

    @Before
    public void before() {
        Mockito.when(repository.findOneUserDtoByFirstname("Louis"))
                .thenReturn(new UserDto("Louis", "Hella", "Xperthis"));
    }

    @Test
    public void testControllerBySearchingOneUser() {

        UserDto userExpected = new UserDto();
        userExpected.setFirstname("Louis");
        userExpected.setLastname("Hella");
        userExpected.setCompanyName("Xperthis");

        UserController userController = new UserController(repository);
        ResponseEntity<ResponseDto<UserDto>> actual = userController.findOneUserByFirstname("Louis");

        assertNotNull("The body is Null", actual.getBody());

        UserDto userActual = actual.getBody().getPayload();
        assertNotNull("The payload is Null", userExpected);

        assertEquals(userExpected.getFirstname(), userActual.getFirstname());
        assertEquals(userExpected.getLastname(), userActual.getLastname());
        assertEquals(userExpected.getCompanyName(), userActual.getCompanyName());
    }

}
