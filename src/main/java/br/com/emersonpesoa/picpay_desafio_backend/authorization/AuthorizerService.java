package br.com.emersonpesoa.picpay_desafio_backend.authorization;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.emersonpesoa.picpay_desafio_backend.transaction.Transaction;

@Service
public class AuthorizerService {

   private static final Logger LOGGER = LoggerFactory
             .getLogger(AuthorizerService.class);
   private RestClient restClient;

   public AuthorizerService(RestClient.Builder builder){
         this.restClient = builder
         .baseUrl("https://9c8a6d1be79b411c80487d318e3ad3c7.api.mockbin.io/")
         .build();
   }
   public void authorize(Transaction transaction){
      LOGGER.info("Authorizing transaction: {}", transaction); 
      var response = restClient.get()
         .retrieve()
         .toEntity(Authorization.class);
        
         if(response.getStatusCode().isError() || !response.getBody().isAuthorized()){
            throw new UnauthorizedTransactionException("Unauthorized transaction %s".formatted(transaction));
         }
      LOGGER.info("Transaction authorized: {}", transaction);
   } 
}
