package data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

//@Entity
public class Support {

    @Id  @GeneratedValue
    @Column(name = "support_id")
    private Long id;
    private int idx; //project 연관간계 맺어야함
    private String userId;
    private String email;
    private String addr;
    private String hp;

    //present 연관관계 맺어야함
    private String presentName;
    private String presentOption;
    private String price;

    private LocalDateTime supportDate;
    private LocalDateTime endDate;
    private int paymentStatus;
}
