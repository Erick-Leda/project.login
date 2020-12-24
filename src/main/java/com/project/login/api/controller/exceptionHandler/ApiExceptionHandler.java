package com.project.login.api.controller.exceptionHandler;

import com.project.login.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handlerNegocio(NegocioException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now().withNano(0));
        problema.setTitulo(ex.getMessage());

        return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var campos = new ArrayList<Problema.Campo>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError)error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Problema.Campo(nome, mensagem));
        }
        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setCampos(campos);
        problema.setDataHora(OffsetDateTime.now().withNano(0));
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");

        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
}
