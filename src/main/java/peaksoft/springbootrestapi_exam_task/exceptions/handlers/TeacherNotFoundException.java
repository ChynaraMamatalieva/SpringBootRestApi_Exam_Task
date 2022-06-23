package peaksoft.springbootrestapi_exam_task.exceptions.handlers;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException() {
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
