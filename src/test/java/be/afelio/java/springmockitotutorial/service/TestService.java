package be.afelio.java.springmockitotutorial.service;

import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.entity.CompanyEntity;
import be.afelio.java.springmockitotutorial.persistence.entity.UserEntity;
import be.afelio.java.springmockitotutorial.persistence.repository.UserRepository;
import be.afelio.java.springmockitotutorial.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestService {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;



    @Test
    public void testService() {
        CompanyEntity companyEntityExpected = new CompanyEntity();
        companyEntityExpected.setName("Xperthis");

        UserEntity userEntityExpected = new UserEntity();
        userEntityExpected.setFirstname("Louis");
        userEntityExpected.setLastname("Hella");
        userEntityExpected.setCompany(companyEntityExpected);

        UserDto userDtoExpected = new UserDto();
        userDtoExpected.setFirstname("Louis");
        userDtoExpected.setFirstname("Hella");
        userDtoExpected.setCompanyName("Xperthis");

        Mockito.when(userRepository.findOneByFirstnameIgnoreCase("Louis"))
                .thenReturn(userEntityExpected);
        Mockito.when(userMapper.mapUserDto(userEntityExpected))
                .thenReturn(userDtoExpected);

        ApplicationService applicationService = new ApplicationService(userRepository, userMapper);
        UserDto userActual = applicationService.findOneUserDtoByFirstname("Louis");

        assertEquals(userDtoExpected.getFirstname(), userActual.getFirstname());
        assertEquals(userDtoExpected.getLastname(), userActual.getLastname());
        assertEquals(userDtoExpected.getCompanyName(), userActual.getCompanyName());
    }
}
