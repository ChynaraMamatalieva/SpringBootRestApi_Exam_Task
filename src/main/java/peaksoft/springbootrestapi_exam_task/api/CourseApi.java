package peaksoft.springbootrestapi_exam_task.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootrestapi_exam_task.dto.request.CompanyRequest;
import peaksoft.springbootrestapi_exam_task.dto.request.CourseRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.CompanyResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.CourseResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Course;
import peaksoft.springbootrestapi_exam_task.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    //find all
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "find all courses")
    public List<CourseResponse> findAll() {
        return courseService.findAll();
    }

    //save
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "save course")
    public CourseResponse save(@RequestBody CourseRequest courseRequest) {
        return courseService.save(courseRequest, courseRequest.getCompanyId());
    }

    //find by id
    @GetMapping("find/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "find course by id")
    public CourseResponse findById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    //update
    @PutMapping("/update/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "update course by id")
    public CourseResponse updateById(@PathVariable Long courseId,
                                     @RequestBody CourseRequest courseRequest) {
        return courseService.updateById(courseId, courseRequest);
    }

    //delete
    @DeleteMapping("/delete/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "delete course by id")
    public SimpleResponse deleteById(@PathVariable Long courseId) {
        return courseService.deleteById(courseId);
    }

}
