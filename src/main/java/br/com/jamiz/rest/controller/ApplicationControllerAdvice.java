package br.com.jamiz.rest.controller;

import br.com.jamiz.Exception.PedidoNaoEncontradoExcpetion;
import br.com.jamiz.Exception.RegraNegocioExcpetion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice { //coloca classe dentro do contexto, que conseguimos fazer um tratamento usando o ExcpetionHandles

    @ExceptionHandler(RegraNegocioExcpetion.class) //marcar método para ser um tratador de erro
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioExcpetion(RegraNegocioExcpetion ex){
        String mensagensErro = ex.getMessage();
        return new ApiErrors(mensagensErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundExcpetion(PedidoNaoEncontradoExcpetion ex){
        return new ApiErrors(ex.getMessage());
    }
}






