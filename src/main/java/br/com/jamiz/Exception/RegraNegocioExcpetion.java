package br.com.jamiz.Exception;

//Classe de tratamento de exceção
public class RegraNegocioExcpetion extends RuntimeException{
    public RegraNegocioExcpetion(String message) {
        super(message);
    }
}
