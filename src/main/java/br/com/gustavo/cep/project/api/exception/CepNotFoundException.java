package br.com.gustavo.cep.project.api.exception;

public class CepNotFoundException extends RuntimeException{

    public CepNotFoundException(String message) {
        super(message);
    }
}
