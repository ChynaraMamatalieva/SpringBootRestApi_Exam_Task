package peaksoft.springbootrestapi_exam_task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.entity.Course;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class CompanyResponse {
    private Long id;
    private String companyName;
    private String locatedCountry;
    private int amountOfCourses;

    public static CompanyResponse from(Company company){
        return new CompanyResponse(company.getId(), company.getCompanyName(), company.getLocatedCountry(),company.getCourses().size());
    }

}
