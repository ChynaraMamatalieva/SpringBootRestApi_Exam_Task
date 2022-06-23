package peaksoft.springbootrestapi_exam_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springbootrestapi_exam_task.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
