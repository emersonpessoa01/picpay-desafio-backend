package br.com.emersonpesoa.picpay_desafio_backend.wallet;

import java.math.BigDecimal;

import jakarta.persistence.Id;

public record Wallet(
        @Id Long id,
        String fullName,
        String cpf,
        String email,
        String password,
        int type,
        BigDecimal balance) {

    public Wallet debit(BigDecimal value) {
        return new Wallet(id,fullName, cpf, email, password, type, balance.subtract(value));
    }

}
