package br.com.emersonpesoa.picpay_desafio_backend.wallet;

import org.springframework.data.repository.ListCrudRepository;

import br.com.emersonpesoa.picpay_desafio_backend.transaction.Transaction;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
