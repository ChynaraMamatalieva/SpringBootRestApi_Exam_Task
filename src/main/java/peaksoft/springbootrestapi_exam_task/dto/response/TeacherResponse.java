package peaksoft.springbootrestapi_exam_task.dto.response;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.entity.Student;
import peaksoft.springbootrestapi_exam_task.entity.Teacher;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Getter
@Setter
public class TeacherResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String courseName;

    public static TeacherResponse from(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setFirstName(teacher.getFirstName());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setEmail(teacher.getEmail());
        //teacherResponse.setCourse(teacher.getCourse());
        // courseResponse.setSizeOfGroups(course.getGroups().size());
        return teacherResponse;
}}
