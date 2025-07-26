package br.com.emersonpesoa.picpay_desafio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan(basePackages = {
        "br.com.emersonpesoa.picpay_desafio_backend.wallet",
        "br.com.emersonpesoa.picpay_desafio_backend.transaction"
})
@EnableJpaAuditing
public class PicpayDesafioBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PicpayDesafioBackendApplication.class, args);
    }
}
