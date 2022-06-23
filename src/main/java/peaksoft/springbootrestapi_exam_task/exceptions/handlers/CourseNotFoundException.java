package peaksoft.springbootrestapi_exam_task.exceptions.handlers;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
