package br.com.jamiz.Exception;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException(){
        super("Senha Invalida");
    }
}
