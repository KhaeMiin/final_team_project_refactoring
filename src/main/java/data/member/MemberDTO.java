package data.member;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("mdto") // 별명 (MemberSQL.xml에서 사용할 수 있도록)
@Getter @Setter
public class MemberDTO {
	private int num; //pk
	private String name;
	private String id;
	private String pass;
	private String photo;
	private String url;
	private String introduce;
	private String hp;
	private String privacy;
	private Timestamp join_date;
	private String email;
	private int auth_status;
	private String authkey;
	private String oauth;


	
}
