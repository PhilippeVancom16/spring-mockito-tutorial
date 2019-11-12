package be.afelio.java.springmockitotutorial.api.dto;

import java.util.Objects;

public class UserDto {

    private String firstname;
    private String lastname;
    private String companyName;

    public UserDto() {
    }

    public UserDto(String firstname, String lastname, String companyName) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.companyName = companyName;
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstname, userDto.firstname) &&
                Objects.equals(lastname, userDto.lastname) &&
                Objects.equals(companyName, userDto.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, companyName);
    }
}
