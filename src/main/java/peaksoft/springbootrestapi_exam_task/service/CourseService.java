package peaksoft.springbootrestapi_exam_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springbootrestapi_exam_task.dto.request.CourseRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.CompanyResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.CourseResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.entity.Teacher;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.CompanyNotFoundException;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.CourseNotFoundException;
import peaksoft.springbootrestapi_exam_task.repository.CompanyRepository;
import peaksoft.springbootrestapi_exam_task.repository.CourseRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    private Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(
                () -> new CourseNotFoundException(
                        "Course with id = " + courseId + " not found!"
                )
        );
    }
    public List<CourseResponse> findAll() {
        return courseRepository.findAll().stream().map(CourseResponse::from).collect(Collectors.toList());
    }

    public CourseResponse save(CourseRequest courseRequest, Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CompanyNotFoundException(
                        "Company with id = " + companyId + " not found!"
                )
        );

        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setCompany(companyRepository.findById(courseRequest.getCompanyId()).orElseThrow(CompanyNotFoundException::new));

        company.setCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
        return CourseResponse.from(course);
    }
    public CourseResponse findById(Long courseId) {

        return CourseResponse.from(courseRepository.findById(courseId).get());
    }
    @Transactional
    public CourseResponse updateById(Long courseId, CourseRequest courseRequest) {
        Course course = getCourseById(courseId);
        //name
        String currentName = course.getCourseName();
        String newName = courseRequest.getCourseName();

        if (currentName != null && !currentName.equals(newName)) {
            course.setCourseName(newName);
        }
        //duration
        int currentDuration = course.getDuration();
        int newDuration = courseRequest.getDuration();

        if(newDuration != 0 && newDuration != currentDuration) {
            course.setDuration(newDuration);
        }

        return CourseResponse.from(course);
    }

    public SimpleResponse deleteById(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if (!exists) {
            throw new CourseNotFoundException(
                    "Course with id = " + courseId + " not found!"
            );
        }
        courseRepository.deleteById(courseId);
        return new SimpleResponse("DELETED!!!", "Course successfully deleted!");
    }
}
