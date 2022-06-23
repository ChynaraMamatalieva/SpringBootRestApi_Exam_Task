package peaksoft.springbootrestapi_exam_task.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Course;



@Getter
@Setter
public class TeacherRequest {

    private String firstName;
    private String email;
    private String lastName;
    private Long courseId;

}
