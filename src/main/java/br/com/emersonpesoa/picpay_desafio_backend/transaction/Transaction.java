package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TRANSACTIONS")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long payer;

    private Long payee;

    @Column(name = "USER_VALUE") // Ajuste necessário para evitar palavra reservada no banco
    private BigDecimal value;

    @CreatedDate
    @Column(name = "CREATED_AT")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")

    private LocalDateTime createdAt;

    // Construtor padrão obrigatório para JPA
    public Transaction() {
    }

    public Transaction(Long id, Long payer, Long payee, BigDecimal value, LocalDateTime createdAt) {
        this.id = id;
        this.payer = payer;
        this.payee = payee;
        setValue(value); // Ajusta escala em setter
        this.createdAt = createdAt;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        if (value != null) {
            this.value = value.setScale(2);
        } else {
            this.value = null;
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
