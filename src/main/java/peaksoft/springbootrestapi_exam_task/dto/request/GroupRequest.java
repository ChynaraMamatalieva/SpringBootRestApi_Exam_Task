package peaksoft.springbootrestapi_exam_task.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.entity.Student;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupRequest {
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
    private List<Long> courseId;

//    private List<Course> courses;
//
//    private List<Student> students;
}
