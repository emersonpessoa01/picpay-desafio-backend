package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import org.springframework.stereotype.Service;

import br.com.emersonpesoa.picpay_desafio_backend.authorization.AuthorizerService;
import br.com.emersonpesoa.picpay_desafio_backend.notification.NotificationService;
import br.com.emersonpesoa.picpay_desafio_backend.wallet.Wallet;
import br.com.emersonpesoa.picpay_desafio_backend.wallet.WalletRepository;
import br.com.emersonpesoa.picpay_desafio_backend.wallet.WalletType;
import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository,
            WalletRepository walletRepository,
            AuthorizerService authorizerService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = new NotificationService();
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        // 1- Validar as tansações com base na regra de
        validate(transaction);
        // 2 - Criar transação
        var newTransaction = transactionRepository.save(transaction);

        // 3 - Debitar da carteira
        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));

        // 4 - Chamar serviços
        // authorize transaction
        authorizerService.authorize(transaction);

        //Notificação
        notificationService.notify(transaction);

        return newTransaction;
    }

    /*
     * - O pagador tem carteira comum
     * - O pagador tem saldo suficiente
     * - 0 pagador não poder ser o recebedor
     */
    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(payer -> isTransactionValid(transaction, payer)
                                ? transaction
                                : null)
                        .orElseThrow(
                                () -> new InvalidTransactionException("Invalid transaction %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException("Invalid transaction %s".formatted(transaction)));

    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue()
                && payer.balance().compareTo(transaction.value()) >= 0
                && !payer.id().equals(transaction.payee());
    }
}
