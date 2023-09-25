package com.example.YICcapstone.global.error;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import com.example.YICcapstone.global.error.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorCode> handleBusinessException(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCode> handleException(Exception e) {
        ErrorCode errorResponse = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorCode> handleAccessDeniedException(AccessDeniedException e) {
        ErrorCode errorResponse = ErrorCode.HANDLE_ACCESS_DENIED;
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorCode> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ErrorCode errorResponse = ErrorCode.METHOD_NOT_ALLOWED;
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorCode> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorCode errorResponse = ErrorCode.INVALID_TYPE_VALUE;
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorCode> handleBindException(BindException e) {
        ErrorCode errorResponse = ErrorCode.INVALID_INPUT_VALUE;
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INVALID_INPUT_VALUE.getCode(),
                ErrorCode.INVALID_INPUT_VALUE.getMessage(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()
        );
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorCode> constraintViolationException(ConstraintViolationException ex) {
        ErrorCode errorResponse = ErrorCode.INVALID_INPUT_VALUE;
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    } // @GetMapping에서 URL {} 매개변수의 @PathVariable의 유효성 검사 실패 시, 500 에러코드 리턴 막으려고 추가로 넣음 - 우준

    // org.hibernate.engine.jdbc.spi.SqlExceptionHelper : NULL not allowed for column "USERNAME"; SQL statement
    // 회원가입 시, 아이디(이메일) 누락했을 때 에러코드 500 발생 -> 해당 오류도 입력값이 잘못되었다는 INVALID_INPUT_VALUE ErrorCode로 대체 필요
}
