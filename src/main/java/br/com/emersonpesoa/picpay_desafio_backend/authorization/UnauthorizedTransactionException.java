package br.com.emersonpesoa.picpay_desafio_backend.authorization;

public class UnauthorizedTransactionException extends RuntimeException {
    //Recebe memsagem como parãmetro do construtor
    public UnauthorizedTransactionException(String message) {
        super(message);
    }

}
