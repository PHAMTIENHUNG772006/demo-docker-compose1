package com.re.hackathon_demo.model.exception;

import com.re.hackathon_demo.model.dto.response.ApiDataResponse;
import com.re.hackathon_demo.model.entity.Medication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MedicationControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : ex.getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Lỗi dữ liệu đầu vào",
                null,
                errors,
                HttpStatus.BAD_REQUEST
        ),HttpStatus.BAD_REQUEST);
    }
}
