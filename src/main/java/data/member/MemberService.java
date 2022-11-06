package data.member;

import java.util.HashMap;

import data.domain.Member;
import data.dto.MemberDTO1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import data.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper mapper;
	private final PasswordEncoder passwordEncoder;

	private final MemberRepository memberRepository;

	/**
	 * 회원가입
	 * @param form
	 */
	@Transactional
	public Member joinMember(MemberDTO1.JoinMemberForm form) {
		Member member = Member.joinMember(form);
		member.hashPassword(passwordEncoder.encode(form.getPass()));
		return memberRepository.save(member);
	}

	public void insertMember(MemberDTO1 dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPass());
		dto.setPass(encodedPassword);
		mapper.insertMember(dto);
	}
	public int getIdCheck(String userId) {
		return mapper.getIdCheck(userId);
	}
	public int getNameCheck(String name) {
		return mapper.getNameCheck(name);
	}
	public int getUrlCheck(String url) {
		return mapper.getUrlCheck(url);
	}
	public int getCheckPass(HashMap<String, String> map) {
		return mapper.getCheckPass(map);
	}
	public int getEmailCheck(String email) {
		return mapper.getEmailCheck(email);
	}
	public MemberDTO1 getAllProfile(HashMap<String, String> map) {
		return mapper.getAllProfile(map);
	}
	public MemberDTO1 getMember(Long member_id) {
		return mapper.getMember(member_id);
	} 
	public void updateMemberPhoto(MemberDTO1 dto) {
		mapper.updateMemberPhoto(dto);
	}
	public void updateMemberUrl(MemberDTO1 dto) {
		mapper.updateMemberUrl(dto);
	}
	public void updateMemberIntroduce(MemberDTO1 dto) {
		mapper.updateMemberIntroduce(dto);
	}
	public void updateMemberName(MemberDTO1 dto) {
		mapper.updateMemberName(dto);
	}
	public void updateMemberPrivacy(MemberDTO1 dto) {
		mapper.updateMemberPrivacy(dto);
	}
	
	public void updateMemberPass(MemberDTO1 dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPass());
		dto.setPass(encodedPassword);
		mapper.updateMemberPass(dto);
	}
	
	public void updateEmailPass(MemberDTO1 dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPass());
		dto.setPass(encodedPassword);
		mapper.updateEmailPass(dto);
	}
	
	public void updateMemberHp(MemberDTO1 dto) {
		mapper.updateMemberHp(dto);
	}
	
	public String getUrl(String user_id) {
		return mapper.getUrl(user_id);
	}
	public String getPhoto(String user_id) {
		return mapper.getPhoto(user_id);
	}
	public String getIntroduce(String user_id) {
		return mapper.getIntroduce(user_id);
	}
	
	public void deleteMember(Long member_id) {
		mapper.deleteMember(member_id);
	}
	
	public String getName(String user_id) {
		return mapper.getName(user_id);
	}
	
	public String getIdUrl(String url) {
		return mapper.getIdUrl(url);
	}
	public int login(HashMap<String, String> map) {
		return mapper.login(map);
	}
	
	
	public MemberDTO1 getAll(String user_id) {
		return mapper.getAll(user_id);
	}
	public MemberDTO1 memberByEmail(String email) {
		return mapper.memberByEmail(email);
	}
	public void updateMemberAuthkey(MemberDTO1 dto) {
		mapper.updateMemberAuthkey(dto);
	}
	public void updateMemberStatus(MemberDTO1 dto) {
		mapper.updateMemberStatus(dto);
	}
	
	
	

}
