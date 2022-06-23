package peaksoft.springbootrestapi_exam_task.exceptions.handlers;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
