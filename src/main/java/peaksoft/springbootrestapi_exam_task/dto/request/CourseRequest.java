package peaksoft.springbootrestapi_exam_task.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.entity.Teacher;

import java.util.List;

@Getter@Setter
public class CourseRequest {
    private String courseName;
    private int duration;


//    private List<Group> groups;
//    private Teacher teacher;
    private Long companyId;
}
