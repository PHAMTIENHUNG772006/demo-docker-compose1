package com.re.hackathon_demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiDataResponse <T>{
    private boolean success;
    private String message;
    private T data;
    private T errorMessages;
    private HttpStatus status;
}
