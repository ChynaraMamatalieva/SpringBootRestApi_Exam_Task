package peaksoft.springbootrestapi_exam_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springbootrestapi_exam_task.dto.request.CompanyRequest;
import peaksoft.springbootrestapi_exam_task.dto.response.CompanyResponse;
import peaksoft.springbootrestapi_exam_task.dto.response.SimpleResponse;
import peaksoft.springbootrestapi_exam_task.entity.Company;
import peaksoft.springbootrestapi_exam_task.exceptions.handlers.CompanyNotFoundException;
import peaksoft.springbootrestapi_exam_task.repository.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    private Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(
                () -> new CompanyNotFoundException(
                        "Company with id = " + companyId + " not found!"
                )
        );
    }

    public List<CompanyResponse> findAll() {
        return companyRepository.findAll().stream().map(CompanyResponse::from).collect(Collectors.toList());
    }

    public CompanyResponse findById(Long companyId) {
        return CompanyResponse.from(getCompanyById(companyId));
    }


    public CompanyResponse save(CompanyRequest companyRequest) {
        Company company = new Company(companyRequest.getCompanyName(), companyRequest.getLocatedCountry());
        companyRepository.save(company);
        return CompanyResponse.from(company);

    }

    public SimpleResponse deleteById(Long companyId) {
        boolean exists = companyRepository.existsById(companyId);

        if (!exists) {
            throw new CompanyNotFoundException(
                    "Company with id = " + companyId + " not found!"
            );
        }
        companyRepository.deleteById(companyId);
        return new SimpleResponse("DELETED!!!", "Company successfully deleted!");
    }

    //update
    @Transactional
    public CompanyResponse updateById(Long companyId, CompanyRequest companyRequest) {

        Company company = getCompanyById(companyId);

        //name
        String currentName = company.getCompanyName();
        String newName = companyRequest.getCompanyName();

        if (currentName != null && !currentName.equals(newName)) {
            company.setCompanyName(newName);
        }

        //located country
        String currentLocatedCountry = company.getLocatedCountry();
        String newLocatedCountry = companyRequest.getLocatedCountry();

        if (newLocatedCountry != null && !newLocatedCountry.equals(currentLocatedCountry)) {
            company.setLocatedCountry(newLocatedCountry);
        }
        return CompanyResponse.from(company);
    }
}
