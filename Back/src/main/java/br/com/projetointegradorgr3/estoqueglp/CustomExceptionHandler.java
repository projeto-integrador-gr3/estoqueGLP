package br.com.projetointegradorgr3.estoqueglp;

import br.com.projetointegradorgr3.estoqueglp.api.dto.DefaultResponse;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.NotFoundException;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.UnprocessableEntityException;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.UsuarioExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultResponse> handle(NotFoundException exception) {
        return new ResponseEntity<>(new DefaultResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<DefaultResponse> handle(UsuarioExistenteException exception) {
        return new ResponseEntity<>(new DefaultResponse(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<DefaultResponse> handle(UnprocessableEntityException exception) {
        return new ResponseEntity<>(new DefaultResponse(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
