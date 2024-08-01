package fon.mas.novica.micronaut.model.dto.role;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public class RoleInfo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
