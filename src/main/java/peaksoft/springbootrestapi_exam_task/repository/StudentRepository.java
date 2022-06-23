package peaksoft.springbootrestapi_exam_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springbootrestapi_exam_task.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
