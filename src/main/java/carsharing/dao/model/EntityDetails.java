package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class EntityDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
}
