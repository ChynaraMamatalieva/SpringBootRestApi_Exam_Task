package peaksoft.springbootrestapi_exam_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springbootrestapi_exam_task.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
