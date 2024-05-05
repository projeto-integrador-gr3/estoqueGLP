package br.com.projetointegradorgr3.estoqueglp.domain.exception;

public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String msg) {
        super(msg);
    }
}
