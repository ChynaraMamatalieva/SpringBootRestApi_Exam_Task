package peaksoft.springbootrestapi_exam_task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.enums.StudyFormat;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "study_format")
    private StudyFormat studyFormat;
    @ManyToOne(cascade = {MERGE})
    private Group group;
    @Transient
    private Long groupId;

    public Student(String firstName, String lastName, String email, StudyFormat studyFormat, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studyFormat = studyFormat;
        this.group = group;
    }



}
