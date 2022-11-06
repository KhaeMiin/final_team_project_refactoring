package com.team.project.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public void joinMember(MemberDto.JoinMemberForm form) {
        if (!form.getPass().equals(form.getPassCheck())) {
            throw new IllegalStateException("비밀번호가 서로 다릅니다.");
        }
        checkPassword(form.getPass(), form.getUserId()); //비밀번호 유효성 검사
        isValidEmail(form.getEmail()); //이메일 형식 체크
        validateDuplicateMember(form.getUserId());//중복아이디 검사
        Member member = Member.joinMember(form);
        member.hashPassword(passwordEncoder.encode(form.getPass()));
        memberRepository.save(member);
    }

    /**
     * 중복 아이디 검증 메서드
     */
    private void validateDuplicateMember(String userId) {
        List<Member> findMembers = memberRepository.findByUserId(userId);
        if (findMembers.size() > 0) { //이 코드가 더 최적화일 것 같다.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    /**
     * 이메일 형식 체크
     */
    private void isValidEmail(String email) {
        boolean validation = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            validation = true;
        }

        if (validation == false) {
            throw new IllegalStateException("이메일 형식으로 작성해주세요");
        }
    }
    /**
     * 비밀번호 영문 숫자 특수문자 조합 체크
     */
    private void checkPassword(String pwd, String id){

        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 10자 이상)
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{10,12}$");
        Matcher passMatcher1 = passPattern1.matcher(pwd);

        if(!passMatcher1.find()){
            throw new IllegalStateException("10자~12자리의 영문(대소문자)+숫자+특수문자 중 2종류 이상을 조합하여 사용할 수 있습니다.");
        }

        // 반복된 문자 확인
        Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1");
        Matcher passMatcher2 = passPattern2.matcher(pwd);

        if(passMatcher2.find()){
            throw new IllegalStateException("같은 문자를 3번 이상 사용하실 수 없습니다.");
        }

        // 아이디 포함 확인
        if(pwd.contains(id)){
            throw new IllegalStateException("비밀번호에 ID를 포함할 수 없습니다.");
        }

        // 특수문자 확인
        Pattern passPattern3 = Pattern.compile("\\W");
        Pattern passPattern4 = Pattern.compile("[!@#$%^*+=-]");

        for(int i = 0; i < pwd.length(); i++){
            String s = String.valueOf(pwd.charAt(i));
            Matcher passMatcher3 = passPattern3.matcher(s);

            if(passMatcher3.find()){
                Matcher passMatcher4 = passPattern4.matcher(s);
                if(!passMatcher4.find()){
                    throw new IllegalStateException("비밀번호에 특수문자는 !@#$^*+=-만 사용 가능합니다.");
                }
            }
        }

        //연속된 문자 확인
        int ascSeqCharCnt = 0; // 오름차순 연속 문자 카운트
        int descSeqCharCnt = 0; // 내림차순 연속 문자 카운트

        char char_0;
        char char_1;
        char char_2;

        int diff_0_1;
        int diff_1_2;

        for(int i = 0; i < pwd.length()-2; i++){
            char_0 = pwd.charAt(i);
            char_1 = pwd.charAt(i+1);
            char_2 = pwd.charAt(i+2);

            diff_0_1 = char_0 - char_1;
            diff_1_2 = char_1 - char_2;

            if(diff_0_1 == 1 && diff_1_2 == 1){
                ascSeqCharCnt += 1;
            }

            if(diff_0_1 == -1 && diff_1_2 == -1){
                descSeqCharCnt -= 1;
            }
        }

        if(ascSeqCharCnt > 1 || descSeqCharCnt > 1){
            throw new IllegalStateException("비밀번호에 연속된 문자열을 사용할 수 없습니다.");
        }

    }

}
