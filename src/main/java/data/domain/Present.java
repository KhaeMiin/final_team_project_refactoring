package data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Present {

    @Id @GeneratedValue
    @Column(name = "present_id")
    private Long num;

    private int idx; //Project 연관관계 맺어야합니다
    private String presentName; //선물 이름
    private String presentOption; //선물 옵션
    private String price; //선물 가격
}
