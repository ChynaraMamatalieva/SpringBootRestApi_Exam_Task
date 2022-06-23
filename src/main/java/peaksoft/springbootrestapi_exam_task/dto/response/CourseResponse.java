package peaksoft.springbootrestapi_exam_task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.entity.Teacher;

import java.util.List;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String courseName;
    private int duration;
    private String companyName;


    public static CourseResponse from(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setCompanyName(course.getCompany().getCompanyName());
        return courseResponse;
    }
}


