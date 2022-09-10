package data.notice;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("ndto")
@Getter @Setter
public class NoticeDTO {
	
	private String num;
	private String id;
	private String subject;
	private String content;
	private String uploadfile;
	private Timestamp writeday;

}
