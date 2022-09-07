package data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id; //pk

    private String name; //사용자 이름
    private String pass;
    private String photo;
    private String url;
    private String introduce;
    private String hp;
    private String privacy;
    private LocalDateTime join_date; //MappedSuperclass 생성예정
    private String email;
    private int auth_status;
    private String authkey;
    private String oauth;

    public Member(String name, String pass, String photo, String url, String introduce, String hp, String privacy, String email, int auth_status, String authkey, String oauth) {
        this.name = name;
        this.pass = pass;
        this.photo = photo;
        this.url = url;
        this.introduce = introduce;
        this.hp = hp;
        this.privacy = privacy;
        this.email = email;
        this.auth_status = auth_status;
        this.authkey = authkey;
        this.oauth = oauth;
        join_date = LocalDateTime.now();
    }

    //==회원 생성 메서드==//
    public static Member createMember(String name, String pass, String photo, String url, String introduce, String hp, String privacy, String email, int auth_status, String authkey, String oauth) { //DTO반환 받을 예정
        return new Member(name, pass, photo, url, introduce, hp, privacy,
                email, auth_status, authkey, oauth);
    }

    //==회원 수정 메서드==//

}
