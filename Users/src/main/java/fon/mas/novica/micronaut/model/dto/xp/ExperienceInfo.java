package fon.mas.novica.micronaut.model.dto.xp;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public class ExperienceInfo {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
