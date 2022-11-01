package data.profile;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LikedDTO {
	
	private String num;
	private String idx;

	private String id; // 나의 아이디
	
	private String name; // 창작자명
	private String category;
	private String title;
	private String thumbnail;
	private int total_amount;
	
	private Date start_date;
	private Date end_date;
}
