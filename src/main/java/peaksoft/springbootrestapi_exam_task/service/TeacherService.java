package peaksoft.springbootrestapi_exam_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springbootrestapi_exam_task.dto.request.StudentRequest;
import peaksoft.springbootrestapi_exam_task.dto.request.TeacherRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.StudentResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.TeacherResponse;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.entity.Student;
import peaksoft.springbootrestapi_exam_task.entity.Teacher;
import peaksoft.springbootrestapi_exam_task.enums.StudyFormat;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.CourseNotFoundException;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.StudentNotFoundException;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.TeacherNotFoundException;
import peaksoft.springbootrestapi_exam_task.repository.CompanyRepository;
import peaksoft.springbootrestapi_exam_task.repository.CourseRepository;
import peaksoft.springbootrestapi_exam_task.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    private Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(
                () -> new TeacherNotFoundException(
                        "Teacher with id = " + teacherId + " not found!"
                )
        );
    }
    public List<TeacherResponse> findAll() {
        return teacherRepository.findAll().stream().map(TeacherResponse::from).collect(Collectors.toList());

    }
    public TeacherResponse save(Long id, TeacherRequest teacherSaveRequest) {
        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new CourseNotFoundException("Course with id"+id+" not found!!!"));
        Teacher teacher = new Teacher(
                teacherSaveRequest.getFirstName(),
                teacherSaveRequest.getLastName(),
                teacherSaveRequest.getEmail());
                //teacherSaveRequest.getCourse());
        teacherRepository.save(teacher);
        course.setTeacher(teacher);
        teacher.setCourse(course);
        return TeacherResponse.from(teacher);
    }

    public TeacherResponse findById(Long teacherId) {
        return TeacherResponse.from(teacherRepository.findById(teacherId).get());
    }

    public TeacherResponse updateById(Long teacherId, TeacherRequest teacherRequest) {

        Teacher teacher = getTeacherById(teacherId);

        //name
        String currentName = teacher.getFirstName();
        String newName = teacherRequest.getFirstName();

        if(currentName != null && !currentName.equals(newName)) {
            teacher.setFirstName(newName);
        }

        //lastname
        String currentLastName = teacher.getLastName();
        String newLastName = teacherRequest.getLastName();

        if(newLastName != null && !newLastName.equals(currentLastName)) {
            teacher.setLastName(newLastName);
        }

        //email
        String currentEmail = teacher.getEmail();
        String newEmail = teacherRequest.getEmail();

        if(newEmail != null && !newEmail.equals(currentEmail)) {
            teacher.setEmail(newEmail);
        }

//        //course
//        Course currentCourse = teacher.getCourse();
//        Course newCourse = teacherRequest.getCourse();
//
//        if (currentCourse != null && !currentCourse.equals(newCourse)) {
//            teacher.setCourse(newCourse);
//        }
        return TeacherResponse.from(teacher);
    }

    public SimpleResponse deleteById(Long teacherId) {
        boolean exists = teacherRepository.existsById(teacherId);

        if (!exists) {
            throw new TeacherNotFoundException(
                    "Teacher with id = " + teacherId + " not found!"
            );
        }
        teacherRepository.deleteById(teacherId);
        return new SimpleResponse("DELETED!!!", "Teacher successfully deleted!!!");
    }
}
