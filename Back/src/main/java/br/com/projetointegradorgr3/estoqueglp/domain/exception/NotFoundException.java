package br.com.projetointegradorgr3.estoqueglp.domain.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String recurso) {
        super(recurso + " não existente ou sem permissão de acesso");
    }
}
