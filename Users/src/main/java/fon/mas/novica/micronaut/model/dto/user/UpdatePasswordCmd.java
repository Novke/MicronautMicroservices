package fon.mas.novica.micronaut.model.dto.user;

public record UpdatePasswordCmd(
        String username,
        String confirmPassword,
        String newPassword
) {
}
