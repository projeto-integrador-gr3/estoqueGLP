package br.com.projetointegradorgr3.estoqueglp.domain.exception;

public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException(String username) {
        super("Usuário \"" + username + "\" já em uso.");
    }
}
