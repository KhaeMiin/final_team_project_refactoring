package data.project;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PresentDTO {
	
	private int num; //pk
	private int idx; //product fk
	private String present_name;
	private String present_option;
	private String price;

}
