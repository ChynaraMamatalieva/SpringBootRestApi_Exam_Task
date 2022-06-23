package peaksoft.springbootrestapi_exam_task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class SimpleResponse {
    private String status;
    private String message;
}
