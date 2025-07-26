package br.com.emersonpesoa.picpay_desafio_backend.notification;

import org.springframework.stereotype.Service;

import br.com.emersonpesoa.picpay_desafio_backend.transaction.Transaction;

@Service
public class NotificationService {
    private final NotificationConsumer notificationConsumer;

    public NotificationService(NotificationConsumer notificationConsumer) {
        this.notificationConsumer = notificationConsumer;
    }
    public void notify(Transaction transaction) {
        // Envia a notificação para o consumidor Kafka
        notificationConsumer.receiveNotification(transaction);
        // Aqui você pode adicionar lógica adicional se necessário, como logging ou manipulação de erros
    }
}
