package fon.mas.novica.micronaut.model.dto.user;

import fon.mas.novica.micronaut.model.dto.role.RoleInfo;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public class UserInfo {

    private String firstName;
    private String lastName;
    private String username;
    private RoleInfo role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleInfo getRole() {
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
