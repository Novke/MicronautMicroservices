package fon.mas.novica.micronaut.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "experience")
public class ExperienceEntity {
    @Id
    @MapsId
    @OneToOne(optional = false)
    private UserEntity user;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer count = 0;

    public Integer increase() {
        return ++count;
    }

    public ExperienceEntity() {
    }

    public ExperienceEntity(UserEntity user, Integer count) {
        this.user = user;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
