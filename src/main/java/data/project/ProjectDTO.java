package data.project;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Alias("pdto")
@Getter @Setter
public class ProjectDTO {
	
	private Long idx; //pk
	private String name; //
	private String id;
	private String category; //카테고리
	private String title; //제목
	private String thumbnail; // 섬네일 이미지명
	private int target_amount;
	private Date start_date; //시작 날짜
	private Date end_date; //종료 날짜
	private String time_start;
	private String project_goal;
	private String project_budget;
	private String project_schedule;
	private String project_team_intro;
	private String project_present_intro;
	private String anticipated_problem;
	private int number_support;
	private int total_amount;
	private MultipartFile upload; // 섬네일 이미지 파일
	private String audit;

}
