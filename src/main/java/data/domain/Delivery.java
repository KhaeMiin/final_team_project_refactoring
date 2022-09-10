package data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column
    private Long id;
    private String userId; //Member 연관관계 맺어야함
    private String name;
    private String addr;
    private String addr2;
    private String hp;
    private int pin;
}
