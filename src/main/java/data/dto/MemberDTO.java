package data.dto;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
	private Long member_id; //pk (num > member_id)
	private String name;
	private String user_id;
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

	/**
	 * 회원가입
	 */
	@Getter @Setter
	@AllArgsConstructor
	public static class JoinMemberForm {
		private String name;
		private String user_id;
		private String pass;
		private String photo;
		private String url;
		private String introduce;
		private String hp;
		private String privacy;
		private String email;
		private int auth_status;
		private String authkey;
		private String oauth;
	}

	
}
