package data.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

//@Entity
@Getter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private int pnum;
    private int fix;
    private int grp;
    private int grph;
    private int grps;
    private int tempdel;
    private int parent_num;
    private String parent;
    private String writer;
    private String photo;
    private String content;
    private LocalDateTime writetime;
}
