package peaksoft.springbootrestapi_exam_task.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootrestapi_exam_task.dto.request.CompanyRequest;
import peaksoft.springbootrestapi_exam_task.dto.request.StudentRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.CompanyResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.StudentResponse;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Student;
import peaksoft.springbootrestapi_exam_task.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/students")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class StudentApi {

    private final StudentService studentService;


    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    //find all
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<StudentResponse> findAll() {
        return studentService.findAll();
    }

    //save
    @PostMapping("/save")
    public StudentResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest.getGroupId(), studentRequest);

    }

    //find by id
    @GetMapping("/find/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    //delete
    @DeleteMapping("/delete/{studentId}")
    public SimpleResponse deleteById(@PathVariable Long studentId) {
        return studentService.deleteById(studentId);
    }

    //update
    @PutMapping("/update/{studentId}")
    public StudentResponse updateById(@PathVariable Long studentId,
                                      @RequestBody StudentRequest studentRequest) {
        return studentService.updateById(studentId, studentRequest);
    }

}
