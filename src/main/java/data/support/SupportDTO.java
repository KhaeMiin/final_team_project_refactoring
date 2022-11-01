package data.support;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SupportDTO {

	private int num;
	private int idx;
	private String id;
	private String email;
	private String addr;
	private String hp;
	private String present_name;
	private String present_option;
	private String price;
	private Date support_date;
	private Date end_date;
	private int payment_status;

}
