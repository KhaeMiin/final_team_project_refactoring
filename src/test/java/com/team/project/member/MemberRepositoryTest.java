package com.team.project.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static com.team.project.member.MemberDto.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원 저장")
    @Test
    void joinMember() {
        JoinMemberForm form = new JoinMemberForm("test", "test", "test@test.com", "test", "test");
        Member member = Member.joinMember(form);
        memberRepository.save(member);
        Member result = memberRepository.findByUserId(form.getUserId()).get(0);
        Assertions.assertThat(result).isEqualTo(member);
    }

}