package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import org.springframework.data.repository.CrudRepository;

import br.com.emersonpesoa.picpay_desafio_backend.wallet.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, Long> {

}