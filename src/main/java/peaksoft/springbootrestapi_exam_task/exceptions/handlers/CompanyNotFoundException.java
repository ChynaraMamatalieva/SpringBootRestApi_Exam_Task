package peaksoft.springbootrestapi_exam_task.exceptions.handlers;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException() {
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
