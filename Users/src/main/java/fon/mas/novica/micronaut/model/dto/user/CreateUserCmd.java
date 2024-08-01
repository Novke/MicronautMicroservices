package fon.mas.novica.micronaut.model.dto.user;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Deserializable
public record CreateUserCmd(
        String username,
        String password,
        String firstName,
        String lastName,
        String email
) {

    @Override
    public String toString() {
        return "CreateUserCmd{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
