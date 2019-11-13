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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestFindAllUser {

    @Mock
    ApplicationService applicationService;

    @Test
    public void testFindAllUser(){

        Mockito.when(applicationService.findAllUserDto())
                .thenReturn(Arrays.asList(
                        new UserDto("Louis", "Hella", "Xperthis"),
                        new UserDto("Philippe", "Vancom", "Xperthis"),
                        new UserDto("Romain", "Gerardy", "Afelio"),
                        new UserDto("Jean", "VanRickstal", "NRB"),
                        new UserDto("Benoit", "Dormaels", "Afelio")
                ));

        UserController userController = new UserController(applicationService);
        ResponseEntity<ResponseDto<List<UserDto>>> actual = userController.findAllUser();
        assertNotNull("The body is Null", actual.getBody());

        List<UserDto> listActual = actual.getBody().getPayload();
        assertNotNull("The payload is Null", listActual);
        assertEquals(5, listActual.size());

        String statusActual = actual.getBody().getStatus();
        assertEquals("SUCCESS", statusActual);

        String messageActual = actual.getBody().getMessage();
        assertEquals("5 users found", messageActual);
    }

    @Test
    public void testFindAllUserNotFound(){

        Mockito.when(applicationService.findAllUserDto())
                .thenReturn(null);

        UserController userController = new UserController(applicationService);
        ResponseEntity<ResponseDto<List<UserDto>>> actual = userController.findAllUser();
        assertNotNull("The body is Null", actual.getBody());

        List<UserDto> listActual = actual.getBody().getPayload();
        assertNull(listActual);

        String statusActual = actual.getBody().getStatus();
        assertEquals("FAILURE", statusActual);

        String messageActual = actual.getBody().getMessage();
        assertEquals("No user found", messageActual);
    }

}
