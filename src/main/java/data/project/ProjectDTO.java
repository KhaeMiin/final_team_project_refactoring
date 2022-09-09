package data.project;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Alias("pdto")
@Getter @Setter
public class ProjectDTO {

	private int idx;
	private String name;
	private String id;
	private String category;
	private String title;
	private String thumbnail;
	private int target_amount;
	private Date start_date;
	private Date end_date;
	private String time_start;
	private String project_goal;
	private String project_budget;
	private String project_schedule;
	private String project_team_intro;
	private String project_present_intro;
	private String anticipated_problem;
	private int number_support;
	private int total_amount;
	private MultipartFile upload;
	private String audit;

}
