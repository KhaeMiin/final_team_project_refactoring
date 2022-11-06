 package data.member;

import java.util.HashMap;

import data.dto.MemberDTO1;

public interface MemberMapper {
	public void insertMember(MemberDTO1 dto);
	
	public int getIdCheck(String userId);
	public int getNameCheck(String name);
	public int getCheckPass(HashMap<String, String> map);
	public int getUrlCheck(String url);
	public MemberDTO1 getMember(Long member_id);
	public void updateMemberName(MemberDTO1 dto);
	public void updateMemberPhoto(MemberDTO1 dto);
	public void updateMemberUrl(MemberDTO1 dto);
	public void updateMemberIntroduce(MemberDTO1 dto);
	public void updateMemberPrivacy(MemberDTO1 dto);
	public void updateMemberPass(MemberDTO1 dto);
	public void updateMemberHp(MemberDTO1 dto);
	public void deleteMember(Long memberId);
	public MemberDTO1 getAllProfile(HashMap<String, String> map);
	public String getName(String user_id);
	public MemberDTO1 getAll(String user_id);
	public int login(HashMap<String, String> map);
	public String getIdUrl(String user_id);
	public int getEmailCheck(String email);
	public void updateMemberAuthkey(MemberDTO1 dto);
	public void updateMemberStatus(MemberDTO1 dto);
	public void updateEmailPass(MemberDTO1 dto);
	public MemberDTO1 memberByEmail(String email);
	
	public String getUrl(String user_id);
	public String getPhoto(String user_id);
	public String getIntroduce(String user_id);

}
