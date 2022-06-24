package peaksoft.springbootrestapi_exam_task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import peaksoft.springbootrestapi_exam_task.dto.request.CompanyRequest;
import peaksoft.springbootrestapi_exam_task.dto.request.StudentRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.*;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.entity.Student;
import peaksoft.springbootrestapi_exam_task.enums.StudyFormat;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.CompanyNotFoundException;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.GroupNotFoundException;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.StudentNotFoundException;
import peaksoft.springbootrestapi_exam_task.repository.GroupRepository;
import peaksoft.springbootrestapi_exam_task.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    private Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(
                        "Student with id = " + studentId + " not found!"
                )
        );
    }

    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream().map(StudentResponse::from).collect(Collectors.toList());
    }

    public StudentResponse save(Long groupId, StudentRequest studentRequest) {
        log.info("save student");
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new GroupNotFoundException(
                        "Group with id = " + groupId + " not found!"
                )
        );
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setGroupId(studentRequest.getGroupId());

        group.setStudent(student);
        student.setGroup(group);
        studentRepository.save(student);
        return StudentResponse.from(student);
    }

    public StudentResponse findById(Long studentId) {
        return StudentResponse.from(studentRepository.findById(studentId).get());
    }

    public StudentResponse updateById(Long studentId, StudentRequest studentRequest) {

        Student student = getStudentById(studentId);

        //name
        String currentName = student.getFirstName();
        String newName = studentRequest.getFirstName();

        if (currentName != null && !currentName.equals(newName)) {
            student.setFirstName(newName);
        }

        //lastname
        String currentLastName = student.getLastName();
        String newLastName = studentRequest.getLastName();

        if (newLastName != null && !newLastName.equals(currentLastName)) {
            student.setLastName(newLastName);
        }

        //email
        String currentEmail = student.getEmail();
        String newEmail = studentRequest.getEmail();

        if (newEmail != null && !newEmail.equals(currentEmail)) {
            student.setEmail(newEmail);
        }
        //study format
        StudyFormat currentStudyFormat = student.getStudyFormat();
        StudyFormat newStudyFormat = studentRequest.getStudyFormat();

        if (currentStudyFormat != null && !currentStudyFormat.equals(newStudyFormat)) {
            student.setStudyFormat(newStudyFormat);
        }
        return StudentResponse.from(student);
    }

    public SimpleResponse deleteById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new StudentNotFoundException(
                    "Student with id = " + studentId + " not found!"
            );
        }
        studentRepository.deleteById(studentId);
        return new SimpleResponse("DELETED!!!", "Student successfully DELETED!!!");
    }
}

