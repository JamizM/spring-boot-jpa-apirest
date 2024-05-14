package br.com.jamiz.rest.controller;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> erros; //quando lançar varios erros, posso mandar objeto paddronizado, contendo um objeto padronizado de Strings

    public ApiErrors(List<String> erros) {
        this.erros = erros;
    }

    public ApiErrors(String message) {
        this.erros = Arrays.asList(message);
    }
}
