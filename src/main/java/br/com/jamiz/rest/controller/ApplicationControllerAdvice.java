package br.com.jamiz.rest.controller;

import br.com.jamiz.Exception.PedidoNaoEncontradoExcpetion;
import br.com.jamiz.Exception.RegraNegocioExcpetion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    private final View error;

    public ApplicationControllerAdvice(View error) {
        this.error = error;
    } //coloca classe dentro do contexto, que conseguimos fazer um tratamento usando o ExcpetionHandles

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidExcpetion(MethodArgumentNotValidException ex){
        List<String> errors = ex
                .getBindingResult()//carrega mensagens de validação e o que falhou
                .getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }
}






