package data.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
@Getter
public class Chat {

    @Id @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    private int room; //방번호
    private String sendId; //발송자
    private String recvId; //수신자
    private String sendTime; //보낸 시간
    private String readTime; //읽은 시간
    private String content; //내용
    private String readCheck; //읽었는지 확인
    private String otherId; //현재 사용자 메세지 상태 id저장
    private String photo; //현재 사용자의 메시지 상태 프로필 저장
    private String userid; //현재 사용자 id
    private int unread; //안읽은 메시지 갯수
    private String exitId; //방을 나간 회원 아이디
    private int exitCount; //방을 나간 회원수

}
