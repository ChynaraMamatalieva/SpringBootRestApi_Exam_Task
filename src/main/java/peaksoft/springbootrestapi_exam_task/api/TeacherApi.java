package peaksoft.springbootrestapi_exam_task.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootrestapi_exam_task.dto.request.StudentRequest;
import peaksoft.springbootrestapi_exam_task.dto.request.TeacherRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.StudentResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.TeacherResponse;
import peaksoft.springbootrestapi_exam_task.entity.Student;
import peaksoft.springbootrestapi_exam_task.entity.Teacher;
import peaksoft.springbootrestapi_exam_task.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("api/teachers")
public class TeacherApi {
    private final TeacherService teacherService;

    public TeacherApi(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //find all
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<TeacherResponse> findAll() {
        return teacherService.findAll();
    }

    //save
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public TeacherResponse save(@RequestBody TeacherRequest teacherRequest) {
        return teacherService.save(teacherRequest.getCourseId(), teacherRequest);

    }

    //find by id
    @GetMapping("/find/{teacherId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public TeacherResponse findById(@PathVariable Long teacherId) {
        return teacherService.findById(teacherId);
    }

    //delete
    @DeleteMapping("/delete/{teacherId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteById(@PathVariable Long teacherId) {
        return teacherService.deleteById(teacherId);
    }

    //update
    @PutMapping("/update/{teacherId}")
    @PreAuthorize("hasAuthority('ADMIN')")

    public TeacherResponse updateById(@PathVariable Long teacherId,
                                      @RequestBody TeacherRequest teacherRequest) {
        return teacherService.updateById(teacherId, teacherRequest);
    }
}
