package data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

//@Entity
public class Notice {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;
    private String userId;
    private String subject;
    private String content;
    private String uploadfile;
    private LocalDateTime writeday;
}
