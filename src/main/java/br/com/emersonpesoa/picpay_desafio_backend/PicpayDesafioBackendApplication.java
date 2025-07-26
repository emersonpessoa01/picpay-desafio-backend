package br.com.emersonpesoa.picpay_desafio_backend;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.config.TopicBuilder;

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
    @Bean
    NewTopic notificationTopic(){
        return TopicBuilder.name("transation-notification")
                .build();
    }
}
