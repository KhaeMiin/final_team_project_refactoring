package data.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static data.dto.MemberDTO.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name; //회원 이름
    private String userId; //회원아이디 (DTO: id)
    private String pass; //비밀번호
    private String photo; //프로필사진
    private String url; //개인 홈페이지 주소
    private String introduce; //회원 소개
    private String hp; //핸드폰번호
    private String privacy;
    private LocalDateTime joinDate; //가입날짜
    private String email; //회원 이메일
    private int authStatus;
    private String authkey;
    private String oauth;

    protected Member(String name, String userId, String pass, String photo, String url, String introduce, String hp, String privacy, String email, int authStatus, String authkey, String oauth) {
        this.name = name;
        this.userId = userId;
        this.pass = pass;
        this.photo = photo;
        this.url = url;
        this.introduce = introduce;
        this.hp = hp;
        this.privacy = privacy;
        this.email = email;
        this.authStatus = authStatus;
        this.authkey = authkey;
        this.oauth = oauth;
        joinDate = LocalDateTime.now();
    }

    //==회원가입 메서드==//
    public static Member joinMember(JoinMemberForm form) {
        return new Member(form.getName(), form.getUser_id(), form.getPass(), form.getPhoto(), form.getUrl(), form.getIntroduce(),
                form.getHp(), form.getPrivacy(), form.getEmail(), form.getAuth_status(), form.getAuthkey(), form.getOauth());
    }

    public Member hashPassword(String passwordEncoder) {
        this.pass = passwordEncoder;
        return this;
    }
}
