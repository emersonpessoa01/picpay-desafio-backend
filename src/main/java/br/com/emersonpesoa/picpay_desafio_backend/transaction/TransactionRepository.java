package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}


