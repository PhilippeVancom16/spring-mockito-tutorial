package be.afelio.java.springmockitotutorial.service.mapper;

import be.afelio.java.springmockitotutorial.api.dto.UserDto;
import be.afelio.java.springmockitotutorial.persistence.entity.CompanyEntity;
import be.afelio.java.springmockitotutorial.persistence.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestUserMapper {

    private UserMapper mapper;

    @Before
    public void setup(){
        mapper = new UserMapper();
    }

    @Test
    public void testUserMapper() {
        // given
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("Xperthis");

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname("Louis");
        userEntity.setLastname("Hella");
        userEntity.setCompany(companyEntity);

        //when
        UserDto userDto = mapper.mapUserDto(userEntity);

        //then
        assertThat(userDto).hasFieldOrPropertyWithValue("firstname", "Louis");
        assertThat(userDto).hasFieldOrPropertyWithValue("lastname", "Hella");
        assertThat(userDto).hasFieldOrPropertyWithValue("companyName", "Xperthis");

        assertEquals(userEntity.getFirstname(), userDto.getFirstname());
        assertEquals(userEntity.getLastname(), userDto.getLastname());
        assertEquals(userEntity.getCompany().getName(), userDto.getCompanyName());
    }
}
