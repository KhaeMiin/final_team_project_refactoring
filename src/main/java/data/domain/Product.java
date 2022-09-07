package data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id; //pk

    //    private String name; //회원 이름
//    private String id; //회원 아이디

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private String category; //카테고리
    private String title; //제목
    private String thumbnail; // 섬네일 이미지명
    private int target_amount; //목표 금액
    private LocalDateTime start_date; //시작 날짜 MappedSuperclass 생성예정
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

    protected Product(Member member, String category, String title, String thumbnail, int target_amount, LocalDateTime end_date, String time_start, String project_goal, String project_budget, String project_schedule, String project_team_intro, String project_present_intro, String anticipated_problem, int number_support, int total_amount, String audit) {
        this.member = member;
        this.category = category;
        this.title = title;
        this.thumbnail = thumbnail;
        this.target_amount = target_amount;
        this.end_date = end_date;
        this.time_start = time_start;
        this.project_goal = project_goal;
        this.project_budget = project_budget;
        this.project_schedule = project_schedule;
        this.project_team_intro = project_team_intro;
        this.project_present_intro = project_present_intro;
        this.anticipated_problem = anticipated_problem;
        this.number_support = number_support;
        this.total_amount = total_amount;
        this.audit = audit;
        this.start_date = LocalDateTime.now();
    }

    //==생성 메서드==//
    public static Product createProduct(Member member, String category, String title, String thumbnail, int target_amount, LocalDateTime end_date, String time_start, String project_goal, String project_budget, String project_schedule, String project_team_intro, String project_present_intro, String anticipated_problem, int number_support, int total_amount, String audit) { //파라미터로 DTO반환받을 예정
        return new Product(member, category, title, thumbnail, target_amount, end_date, time_start, project_goal, project_budget, project_schedule, project_team_intro,
                project_present_intro, anticipated_problem, number_support, total_amount, audit);
    }
}
