package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "TRANSACTIONS")
public record Transaction(
    @Id Long id,
    Long payer,
    Long payee,
    BigDecimal value,
    @CreatedDate LocalDateTime createAt
) {
    public Transaction{
        // Definir o valor com duas casas decimais
        value = value.setScale(2);
    }

}
