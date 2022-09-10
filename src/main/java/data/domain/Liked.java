package data.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

//@Entity
@Getter
public class Liked {

    @Id @GeneratedValue
    @Column(name = "liked_id")
    private Long id;

    private int projectId; //project연관관계 맺어야함
    private String name; //창작자명
    private String userId; //나의 아이디
    private String title;
    private String thumbnail;
    private int totalAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
