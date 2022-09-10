package data.project;

import org.apache.ibatis.type.Alias;

@Alias("pstdto")
public class PresentDTO {
	
	private int num; //pk
	private int idx; //product fk
	private String present_name;
	private String present_option;
	private String price;

}
