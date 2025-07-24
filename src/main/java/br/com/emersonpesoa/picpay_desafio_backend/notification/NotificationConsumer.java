package br.com.emersonpesoa.picpay_desafio_backend.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.emersonpesoa.picpay_desafio_backend.transaction.Transaction;

@Service
public class NotificationConsumer {
   private RestClient restClient;
   
   public NotificationConsumer(RestClient.Builder builder) {
         this.restClient = builder
         .baseUrl("https://9c8a6d1be79b411c80487d318e3ad3c7.api.mockbin.io/")
         .build();
   }
   @KafkaListener(topics = "transation-notification", groupId = "picpay-desafio-backend")
   public void receiveNotification(Transaction transaction) {
      var response = restClient.get()
         .retrieve()
         .toEntity(Notification.class);
        
         if(response.getStatusCode().isError() || !response.getBody().message()) {
            throw new NotificationException("Erro ao enviar notificação");
         }
         

   }
      
}
