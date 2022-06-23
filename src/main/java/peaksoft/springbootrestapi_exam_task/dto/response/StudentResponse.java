package peaksoft.springbootrestapi_exam_task.dto.response;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.entity.Student;
import peaksoft.springbootrestapi_exam_task.enums.StudyFormat;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import static javax.persistence.CascadeType.MERGE;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
    private String groupName;

    public static StudentResponse from(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
        studentResponse.setGroupName(student.getGroup().getGroupName());
        // courseResponse.setSizeOfGroups(course.getGroups().size());
        return studentResponse;
    }
}
