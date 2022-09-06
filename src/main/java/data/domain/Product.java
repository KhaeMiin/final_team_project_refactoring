package data.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Product {

    @Id @GeneratedValue
    private Long product_id; //pk

    private String name; //
    private String id;
    private String category; //카테고리
    private String title; //제목
    private String thumbnail; // 섬네일 이미지명
    private int target_amount; //목표 금액
    private LocalDateTime start_date; //시작 날짜
    private LocalDateTime end_date; //종료 날짜
    private String time_start; //시작 시간
    private String project_goal;
    private String project_budget;
    private String project_schedule;
    private String project_team_intro;
    private String project_present_intro;
    private String anticipated_problem;
    private int number_support;
    private int total_amount;
    private String audit;
}
