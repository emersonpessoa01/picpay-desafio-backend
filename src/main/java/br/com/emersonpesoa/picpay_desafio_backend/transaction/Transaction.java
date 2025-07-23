package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Id;

public record Transaction(
    @Id Long id,
    Long payer,
    Long payee,
    BigDecimal value,
    LocalDateTime createAt
) {

}
