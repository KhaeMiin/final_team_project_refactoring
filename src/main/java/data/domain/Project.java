package data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@Getter
@NoArgsConstructor
public class Project {

    @Id @GeneratedValue
    @Column(name = "project_id")
    private Long id; //pk(idx)

/*    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //기존dto에서 name, id*/

    private String name;
    private String userId;

    private String category;
    private String title;
    private String thumbnail;
    private int targetAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String timeStart;
    private String projectGoal;
    private String projectBudget;
    private String projectSchedule;
    private String projectTeamIntro;
    private String projectPresentIntro;
    private String anticipatedProblem;
    private int numberSupport;
    private int totalAmount;
    private String audit;


}
