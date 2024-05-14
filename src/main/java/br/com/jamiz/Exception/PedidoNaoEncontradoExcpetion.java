package br.com.jamiz.Exception;

public class PedidoNaoEncontradoExcpetion extends RuntimeException {
    public PedidoNaoEncontradoExcpetion(){
        super("Pedido Nao encontrado");
    }
}
