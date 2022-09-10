 package data.member;

import java.util.HashMap;

import data.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	public void insertMember(MemberDTO dto);
	
	public int getIdCheck(String userId);
	public int getNameCheck(String name);
	public int getCheckPass(HashMap<String, String> map);
	public int getUrlCheck(String url);
	public MemberDTO getMember(Long member_id);
	public void updateMemberName(MemberDTO dto);
	public void updateMemberPhoto(MemberDTO dto);
	public void updateMemberUrl(MemberDTO dto);
	public void updateMemberIntroduce(MemberDTO dto);
	public void updateMemberPrivacy(MemberDTO dto);
	public void updateMemberPass(MemberDTO dto);
	public void updateMemberHp(MemberDTO dto);
	public void deleteMember(Long memberId);
	public MemberDTO getAllProfile(HashMap<String, String> map);
	public String getName(String user_id);
	public MemberDTO getAll(String user_id);
	public int login(HashMap<String, String> map);
	public String getIdUrl(String user_id);
	public int getEmailCheck(String email);
	public void updateMemberAuthkey(MemberDTO dto);
	public void updateMemberStatus(MemberDTO dto);
	public void updateEmailPass(MemberDTO dto);
	public MemberDTO memberByEmail(String email);
	
	public String getUrl(String user_id);
	public String getPhoto(String user_id);
	public String getIntroduce(String user_id);

}
