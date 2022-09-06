package data.member;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("mdto") // 별명 (MemberSQL.xml에서 사용할 수 있도록)
@Getter @Setter
public class MemberDTO {
	private Long num; //pk
	private String name; //사용자 이름
	private String id; //아이디
	private String pass; //비밀번호
	private String photo; // 프로필사진
	private String url; //
	private String introduce;
	private String hp; //폰 번호
	private String privacy;
	private LocalDateTime join_date; //가입날짜
	private String email;
	private int auth_status;
	private String authkey;
	private String oauth;


	
}
