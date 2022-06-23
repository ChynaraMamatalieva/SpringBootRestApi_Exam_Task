package peaksoft.springbootrestapi_exam_task.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    private int duration;

    @ManyToOne
    private Company company;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<>();

    @OneToOne(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Teacher teacher;

    public Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;

    }
//
//    public Course(String courseName, int duration, Company company, List<Group> groups, Teacher teacher) {
//        this.courseName = courseName;
//        this.duration = duration;
//        this.company = company;
//        this.groups = groups;
//        this.teacher = teacher;
//    }

    public void setGroup(Group group) {
        this.groups.add(group);
    }
}
