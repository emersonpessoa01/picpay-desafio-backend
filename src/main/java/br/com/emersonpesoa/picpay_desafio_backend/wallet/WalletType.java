package br.com.emersonpesoa.picpay_desafio_backend.wallet;


public enum WalletType {
    COMUM(1),
    LOJISTA(2);

    private final int value;

    WalletType(int value) {
        this.value = value;
    }
}
