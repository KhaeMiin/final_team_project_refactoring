package com.team.project.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.team.project.member.MemberDto.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원가입 성공")
    @Test
    void join() {
        memberService.joinMember(new JoinMemberForm("test", "test", "test@test.com", "asd123456+", "asd123456+"));
        Member member = memberRepository.findByUserId("test").get(0);
        Assertions.assertThat(member.getUserId()).isEqualTo("test");
        Assertions.assertThat(member.getNickname()).isEqualTo("test");
    }

    @DisplayName("회원가입 실패 - 중복아이디")
    @Test
    void join_error1() {
        memberService.joinMember(new JoinMemberForm("test", "test1", "test1@test.com", "asd123456+", "asd123456+"));
        try {
            memberService.joinMember(new JoinMemberForm("test", "test2", "test2@test.com", "asd123456+", "asd123456+"));
        } catch (Exception e) {
            return;
        }

        fail();
        assertThrows(IllegalStateException.class, () -> memberService.joinMember(new JoinMemberForm("test", "test2", "test2@test.com", "asd123456+", "asd123456+")));
    }
}