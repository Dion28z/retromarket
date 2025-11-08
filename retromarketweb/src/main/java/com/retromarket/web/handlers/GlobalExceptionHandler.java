package com.retromarket.web.handlers;

import com.retromarket.facade.model.common.GenericResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    GenericResponseDTO responseDTO = new GenericResponseDTO(false);
    responseDTO.setException(ex.getMessage());
    responseDTO.setMessage("common.validation.missingParameter");
    return super.handleExceptionInternal(ex, responseDTO, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED,
        request);
  }
}
