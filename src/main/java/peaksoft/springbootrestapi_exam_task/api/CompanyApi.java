package peaksoft.springbootrestapi_exam_task.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootrestapi_exam_task.dto.request.CompanyRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.CompanyResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;

    //find all
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "find all companies")
    public List<CompanyResponse> findAll() {
        return companyService.findAll();
    }

    //find by id
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/find/{companyId}")
    @Operation(summary = "find company by id")
    public CompanyResponse findById(@PathVariable Long companyId) {
        return companyService.findById(companyId);
    }

    //save
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "save company")
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.save(companyRequest);

    }

    //delete
    @DeleteMapping("/delete/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "delete company by id")
    public SimpleResponse deleteById(@PathVariable Long companyId) {
        return companyService.deleteById(companyId);
    }

    //update
    @PutMapping("/update/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "update company by id")
    public CompanyResponse updateById(@PathVariable Long companyId,
                                      @RequestBody CompanyRequest companyRequest) {
        return companyService.updateById(companyId, companyRequest);
    }


}
