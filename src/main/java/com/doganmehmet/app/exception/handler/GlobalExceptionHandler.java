package com.doganmehmet.app.exception.handler;

import com.doganmehmet.app.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private String getHostName()
    {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.error("Unable to get host name", e);
        }

        return "UnknownHost";
    }

    private <E> ApiError<E> createApiError(E message, WebRequest request)
    {
        var apiError = new ApiError<E>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        var exception = new MyException<E>();
        exception.setPath(request.getDescription(false).substring(4));
        exception.setErrorTime(new Date());
        exception.setMessage(message);
        exception.setHostName(getHostName());

        apiError.setException(exception);

        return apiError;
    }

    private List<String> addValue(List<String> list, String value)
    {
        list.add(value);
        return list;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request)
    {
        return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<HashMap<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request)
    {
        var map = new HashMap<String, List<String>>();

        for (var objError: ex.getBindingResult().getAllErrors()) {
            var fieldName = ((FieldError)objError).getField();

            if (map.containsKey(fieldName))
                map.put(fieldName, addValue(map.get(fieldName), objError.getDefaultMessage()));
            else
                map.put(fieldName, addValue(new ArrayList<>(), objError.getDefaultMessage()));
        }

        return ResponseEntity.badRequest().body(createApiError(map, request));
    }
}
