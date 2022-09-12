package data.member;

import com.team.project.FinalProjectApplication;
import data.domain.Member;
import data.dto.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import data.repository.MemberRepository;

import static data.dto.MemberDTO.*;

@SpringBootTest(classes = FinalProjectApplication.class)
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberMapper memberMapper;

    @Test
    void join() {
        //given
        JoinMemberForm form = new JoinMemberForm("name", "userid", "pass1234asd+", "pass.jpg", "sdfsdf", "sfdsf", "0102929292",
                "privacy", "asd@sdfsdf", 2, "authkey", "oauth");
        //when
        Member member = memberService.joinMember(form);
        //then
        Assertions.assertThat(member.getUserId()).isEqualTo(form.getUser_id());
    }

}