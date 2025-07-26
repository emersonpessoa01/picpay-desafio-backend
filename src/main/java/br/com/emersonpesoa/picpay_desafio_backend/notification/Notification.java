package br.com.emersonpesoa.picpay_desafio_backend.notification;

public class Notification {

    private String message;

    // Construtor padrão (obrigatório para desserialização Jackson)
    public Notification() {
    }

    // Construtor com parâmetro
    public Notification(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }

    // Método auxiliar para interpretar se a mensagem é autorizada
    public boolean isAuthorized() {
        return "Autorizado".equalsIgnoreCase(message);
    }
}
