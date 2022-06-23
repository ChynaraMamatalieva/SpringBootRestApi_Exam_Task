package peaksoft.springbootrestapi_exam_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springbootrestapi_exam_task.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
