package peaksoft.springbootrestapi_exam_task.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootrestapi_exam_task.dto.request.CompanyRequest;
import peaksoft.springbootrestapi_exam_task.dto.request.GroupRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.CompanyResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.GroupResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Group;
import peaksoft.springbootrestapi_exam_task.service.GroupService;

import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GroupApi {
    private final GroupService groupService;

    public GroupApi(GroupService groupService) {
        this.groupService = groupService;
    }

    //find all
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<GroupResponse> findAll() {
        return groupService.findAll();
    }

    //save
    @PostMapping("/save")
    public GroupResponse save(@RequestBody GroupRequest groupRequest) {
        return groupService.save(groupRequest);

    }

    //find by id
    @GetMapping("/find/{groupId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GroupResponse findById(@PathVariable Long groupId) {
        return groupService.findById(groupId);
    }

    //update
    @PutMapping("/update/{groupId}")
    public GroupResponse updateById(@PathVariable Long groupId,
                                    @RequestBody GroupRequest groupRequest) {
        return groupService.updateById(groupId, groupRequest);
    }

    //delete
    @DeleteMapping("/delete/{groupId}")
    public SimpleResponse deleteById(@PathVariable Long groupId) {
        return groupService.deleteById(groupId);
    }
}
