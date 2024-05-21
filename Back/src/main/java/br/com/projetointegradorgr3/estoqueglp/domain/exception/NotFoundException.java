package br.com.projetointegradorgr3.estoqueglp.domain.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String recurso, Integer id) {
        super(recurso + " '" + id + "' não existente!");
    }
}
