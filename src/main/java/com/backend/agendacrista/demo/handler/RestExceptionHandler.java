package com.backend.agendacrista.demo.handler;

import com.backend.agendacrista.demo.error.ErrorDetails;
import com.backend.agendacrista.demo.error.ResourceNotFoundException;
import com.backend.agendacrista.demo.error.UserPricipalNotAutorizedException;
import com.backend.agendacrista.demo.error.ValidationErroDetails;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundDetails(ResourceNotFoundException rnfExeption) {
        ErrorDetails rnfDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Recurso não encontrado")
                .detail(rnfExeption.getMessage())
                .developerMessage(rnfExeption.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(AuthenticationException bcExeption) {
        ErrorDetails bcDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Falha ao efetuar autenticação")
                .detail("Email ou senhas inválidos")
                .developerMessage(bcExeption.getClass().getName())
                .build();
        return new ResponseEntity<>(bcDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> handleDisabledException(DisabledException dExeption) {
        ErrorDetails dDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Falha ao efetuar autenticação")
                .detail("Confirmação de email pendente, faça novamente o registro")
                .developerMessage(dExeption.getClass().getName())
                .build();
        return new ResponseEntity<>(dDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException aExeption) {
        ErrorDetails aDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Falha ao efetuar autenticação")
                .detail(aExeption.getMessage())
                .developerMessage(aExeption.getClass().getName())
                .build();
        return new ResponseEntity<>(aDetails, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvExeption, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = manvExeption.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErroDetails rnfDetails = ValidationErroDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Erro de validação")
                .detail(manvExeption.getMessage())
                .developerMessage(manvExeption.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException prExeption) {
        ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Propriedade inválida")
                .detail(prExeption.getMessage())
                .developerMessage(prExeption.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserPricipalNotAutorizedException.class)
    public ResponseEntity<?> handleUserPricipalNotAutorizedException(UserPricipalNotAutorizedException upnaException) {
        ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Usuário não tem permissão")
                .detail(upnaException.getMessage())
                .developerMessage(upnaException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<?> handleUnsupportedOperationException(UnsupportedOperationException uoException) {
        ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Operação não suportada")
                .detail(uoException.getMessage())
                .developerMessage(uoException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //MethodArgumentTypeMismatchException tratar

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Exeção Interna")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return super.handleExceptionInternal(ex, errorDetails, headers, status, request);
    }
}
