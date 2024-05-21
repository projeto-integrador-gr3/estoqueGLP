package br.com.projetointegradorgr3.estoqueglp.api;

import br.com.projetointegradorgr3.estoqueglp.api.dto.DefaultResponse;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.NotFoundException;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.UnprocessableEntityException;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.UsuarioExistenteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collection;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultResponse> handle(NotFoundException exception) {
        return new ResponseEntity<>(new DefaultResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<DefaultResponse> handle(UsuarioExistenteException exception) {
        return new ResponseEntity<>(new DefaultResponse(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<DefaultResponse> handle(UnprocessableEntityException exception) {
        return new ResponseEntity<>(new DefaultResponse(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> globalErrors = ex.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(objectError -> objectError.getObjectName() + ": " + objectError.getDefaultMessage())
                .toList();

        List<String> fieldErros = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        List<String> errors = List.of(globalErrors, fieldErros).stream().flatMap(Collection::stream).toList();

        return super.handleExceptionInternal(ex, new DefaultResponse(errors), headers, status, request);
    }
}
