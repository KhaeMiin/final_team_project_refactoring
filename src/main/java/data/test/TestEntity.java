package data.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int age;
}
