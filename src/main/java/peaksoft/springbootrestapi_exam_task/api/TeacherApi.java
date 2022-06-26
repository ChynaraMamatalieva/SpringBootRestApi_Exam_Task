package peaksoft.springbootrestapi_exam_task.api;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "find all teachers")
    public List<TeacherResponse> findAll() {
        return teacherService.findAll();
    }

    //save
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "save teacher")
    public TeacherResponse save(@RequestBody TeacherRequest teacherRequest) {
        return teacherService.save(teacherRequest.getCourseId(), teacherRequest);

    }

    //find by id
    @GetMapping("/find/{teacherId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "find teacher by id")
    public TeacherResponse findById(@PathVariable Long teacherId) {
        return teacherService.findById(teacherId);
    }

    //delete
    @DeleteMapping("/delete/{teacherId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "delete teacher by id")
    public SimpleResponse deleteById(@PathVariable Long teacherId) {
        return teacherService.deleteById(teacherId);
    }

    //update
    @PutMapping("/update/{teacherId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "update teacher by id")
    public TeacherResponse updateById(@PathVariable Long teacherId,
                                      @RequestBody TeacherRequest teacherRequest) {
        return teacherService.updateById(teacherId, teacherRequest);
    }
}
