package peaksoft.springbootrestapi_exam_task.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootrestapi_exam_task.entity.Course;

import java.util.List;

@Getter @Setter
public class CompanyRequest {
    private String companyName;
    private String locatedCountry;


}
