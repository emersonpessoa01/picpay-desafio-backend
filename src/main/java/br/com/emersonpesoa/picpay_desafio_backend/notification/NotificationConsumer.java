package br.com.emersonpesoa.picpay_desafio_backend.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.emersonpesoa.picpay_desafio_backend.authorization.AuthorizerService;
import br.com.emersonpesoa.picpay_desafio_backend.transaction.Transaction;

@Service
public class NotificationConsumer {
      private static final Logger LOGGER = LoggerFactory
             .getLogger(AuthorizerService.class);

   private RestClient restClient;
   
   public NotificationConsumer(RestClient.Builder builder) {
         this.restClient = builder
         .baseUrl("https://9c8a6d1be79b411c80487d318e3ad3c7.api.mockbin.io/")
         .build();
   }
   @SuppressWarnings("null")
   @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
   public void receiveNotification(Transaction transaction) {
      LOGGER.info("Authorizing transaction: {}", transaction); 

      var response = restClient.get()
         .retrieve()
         .toEntity(Notification.class);
        
         if(response.getStatusCode().isError() || response.getBody().getMessage() == null || response.getBody().getMessage().isEmpty()) {
            throw new NotificationException("Erro ao enviar notificação");
         }
         LOGGER.info("Notification sent successfully for transaction: {}", response.getBody().getMessage());
         

   }
      
}
