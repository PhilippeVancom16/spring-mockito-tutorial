package be.afelio.java.springmockitotutorial.controller;

import be.afelio.java.springmockitotutorial.api.dto.ResponseDto;
import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestFindOneUserByFirstname {

    @Mock
    ApplicationService applicationService;

    @Test
    public void testFindOneUserByFirstname() {

        Mockito.when(applicationService.findOneUserDtoByFirstname("Louis"))
                .thenReturn(new UserDto("Louis", "Hella", "Xperthis"));

        UserDto userExpected = new UserDto();
        userExpected.setFirstname("Louis");
        userExpected.setLastname("Hella");
        userExpected.setCompanyName("Xperthis");

        UserController userController = new UserController(applicationService);
        ResponseEntity<ResponseDto<UserDto>> actual = userController.findOneUserByFirstname("Louis");
        assertNotNull("The body is Null", actual.getBody());

        UserDto userActual = actual.getBody().getPayload();
        assertNotNull("The payload is Null", userActual);
        assertEquals(userExpected.getFirstname(), userActual.getFirstname());
        assertEquals(userExpected.getLastname(), userActual.getLastname());
        assertEquals(userExpected.getCompanyName(), userActual.getCompanyName());

        String statusActual = actual.getBody().getStatus();
        assertEquals("SUCCESS", statusActual);

        String messageActual = actual.getBody().getMessage();
        assertEquals("User found", messageActual);
    }

    @Test
    public void testFindOneUserByFirstnameNotFound() {

        Mockito.when(applicationService.findOneUserDtoByFirstname("Thibault"))
                .thenReturn(null);

        UserController userController = new UserController(applicationService);
        ResponseEntity<ResponseDto<UserDto>> actual = userController.findOneUserByFirstname("Thibault");
        assertNotNull("The body is Null", actual.getBody());

        UserDto userActual = actual.getBody().getPayload();
        assertNull(userActual);

        String statusActual = actual.getBody().getStatus();
        assertEquals("FAILURE", statusActual);

        String messageActual = actual.getBody().getMessage();
        assertEquals("No user found", messageActual);
    }
}
