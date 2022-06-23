package peaksoft.springbootrestapi_exam_task.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.enums.StudyFormat;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import static javax.persistence.CascadeType.MERGE;

@Getter @Setter
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
    private Long groupId;

}
