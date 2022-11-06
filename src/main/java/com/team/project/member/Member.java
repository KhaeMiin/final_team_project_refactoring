package com.team.project.member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname; //닉네임
    private String userId; //아이디
    private String pass; //비밀번호
    private String photo; //프로필?
    private String url; //개인 url
    private String introduce; //간단한 자기소개
    private String hp; //핸드폰
    private String privacy;
    private LocalDateTime joinDate; //가입 일자
    private String email; //이메일
    private int authStatus;
    private String authKey;
    private String oauth;

    private Member(String nickname, String userId, String pass, String photo, String url, String introduce, String hp, String privacy, LocalDateTime joinDate, String email, int authStatus, String authKey, String oauth) {
        this.nickname = nickname;
        this.userId = userId;
        this.pass = pass;
        this.photo = photo;
        this.url = url;
        this.introduce = introduce;
        this.hp = hp;
        this.privacy = privacy;
        this.joinDate = joinDate;
        this.email = email;
        this.authStatus = authStatus;
        this.authKey = authKey;
        this.oauth = oauth;
    }

    private Member(String nickname, String userId, String pass, LocalDateTime joinDate, String email) {
        this.nickname = nickname;
        this.userId = userId;
        this.pass = pass;
        this.joinDate = joinDate;
        this.email = email;
    }

    //==생성 메서드(회원가입)==//
    public static Member joinMember(MemberDto.JoinMemberForm form) {
        return new Member(form.getNickname(), form.getUserId(), form.getPass(), LocalDateTime.now(), form.getEmail());
    }

    //==비밀번호 암호화==//
    public Member hashPassword(String passwordEncoder) {
        this.pass = passwordEncoder;
        return this;
    }

    //==회원 전화번호 수정==//
    public void updateMemberHp(String hp) {
        this.hp = hp;
    }
}
