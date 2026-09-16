package com.br.test.f1rst.handler;

import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler {
  @Autowired private MessageUtil messageUtil;

  @ExceptionHandler(APIException.class)
  public ResponseEntity<ErrorApiResponse> handlerAPIException(APIException ex) {
    String message =
        ex.getErrorCode() != null
            ? messageUtil.getMessage(ex.getErrorCode(), ex.getArgs())
            : ex.getMessage();
    ErrorApiResponse response =
        ErrorApiResponse.builder().message(message).description(ex.getMessage()).build();
    return ResponseEntity.status(ex.getStatusException()).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorApiResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    String fieldErrors =
        ex.getBindingResult().getAllErrors().stream()
            .map(
                error -> {
                  String fieldName =
                      error instanceof FieldError fe ? fe.getField() : error.getObjectName();
                  return fieldName + ": " + error.getDefaultMessage();
                })
            .collect(Collectors.joining("; "));
    return ResponseEntity.badRequest()
        .body(
            ErrorApiResponse.builder()
                .message(messageUtil.getMessage(ErrorCode.REQUEST_CORPO_INVALIDO))
                .description(fieldErrors)
                .build());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorApiResponse> handleConstraintViolationExceptions(
      ConstraintViolationException ex) {
    String fieldErrors =
        ex.getConstraintViolations().stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .collect(Collectors.joining("; "));
    return ResponseEntity.badRequest()
        .body(
            ErrorApiResponse.builder()
                .message(
                    messageUtil.getMessage(
                        ErrorCode.CONSTRAINT_VIOLATION, fieldErrors))
                .description(fieldErrors)
                .build());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorApiResponse> handleRequestBodyMissingExceptions(
      HttpMessageNotReadableException ex) {
    return ResponseEntity.badRequest()
        .body(
            ErrorApiResponse.builder()
                .message(messageUtil.getMessage(ErrorCode.REQUEST_CORPO_INVALIDO))
                .build());
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorApiResponse> handleMissingRequestParameterExceptions(
      MissingServletRequestParameterException ex) {
    String message =
        messageUtil.getMessage(ErrorCode.REQUEST_PARAMETRO_AUSENTE, ex.getParameterName());
    return ResponseEntity.badRequest().body(ErrorApiResponse.builder().message(message).build());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorApiResponse> handleTypeMismatchExceptions(
      MethodArgumentTypeMismatchException ex) {
    String message =
        messageUtil.getMessage(ErrorCode.REQUEST_PARAMETRO_INVALIDO, ex.getName());
    return ResponseEntity.badRequest()
        .body(
            ErrorApiResponse.builder()
                .message(message)
                .description(ex.getValue() != null ? "Valor recebido: " + ex.getValue() : null)
                .build());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorApiResponse> handleMethodNotSupportedExceptions(
      HttpRequestMethodNotSupportedException ex) {
    String message =
        messageUtil.getMessage(ErrorCode.REQUEST_METODO_NAO_SUPORTADO, ex.getMethod());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(ErrorApiResponse.builder().message(message).build());
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorApiResponse> handleMediaTypeNotSupportedExceptions(
      HttpMediaTypeNotSupportedException ex) {
    String supported =
        String.join(", ", ex.getSupportedMediaTypes().stream().map(String::valueOf).toList());
    String message =
        messageUtil.getMessage(ErrorCode.REQUEST_MEDIA_TYPE_NAO_SUPORTADO, supported);
    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        .body(ErrorApiResponse.builder().message(message).build());
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorApiResponse> handleNoResourceFoundException(
      NoResourceFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
            ErrorApiResponse.builder()
                .message(messageUtil.getMessage(ErrorCode.RECURSO_NAO_ENCONTRADO))
                .build());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorApiResponse> handleDataIntegrityViolations(
      DataIntegrityViolationException ex) {
    log.error("Integrity violation: ", ex);
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(ErrorApiResponse.builder().message(messageUtil.getMessage(ErrorCode.INTEGRIDADE_DADOS)).build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorApiResponse> handlerGenericException(Exception ex) {
    log.error("Exception: ", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ErrorApiResponse.builder()
                .message(messageUtil.getMessage(ErrorCode.ERRO_INTERNO))
                .build());
  }
}