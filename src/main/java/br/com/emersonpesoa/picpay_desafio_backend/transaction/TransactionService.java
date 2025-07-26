package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import java.util.List;

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
            AuthorizerService authorizerService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        // 1- Validar as tansações com base na regra de
        validate(transaction);
        // 2 - Criar transação
        var newTransaction = transactionRepository.save(transaction);

        // 3 - Debitar da carteira
        var walletPayer = walletRepository.findById(transaction.getPayer()).get();
        var walletPayee = walletRepository.findById(transaction.getPayee()).get();

        // Atualizar as carteiras
        walletRepository.save(walletPayer.debit(transaction.getValue()));
        walletRepository.save(walletPayee.credit(transaction.getValue()));

        // 4 - Chamar serviços
        // authorize transaction
        authorizerService.authorize(transaction);

        // Notificação
        notificationService.notify(transaction);

        return newTransaction;
    }

    /*
     * - O pagador tem carteira comum
     * - O pagador tem saldo suficiente
     * - 0 pagador não poder ser o recebedor
     */
    private void validate(Transaction transaction) {
        walletRepository.findById(transaction.getPayee())
                .map(payee -> walletRepository.findById(transaction.getPayer())
                        .map(payer -> isTransactionValid(transaction, payer)
                                ? transaction
                                : null)
                        .orElseThrow(
                                () -> new InvalidTransactionException("Invalid transaction %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException("Invalid transaction %s".formatted(transaction)));

    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.getType() == WalletType.COMUM.getValue()
                && payer.getBalance().compareTo(transaction.getValue()) >= 0
                && !payer.getId().equals(transaction.getPayee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
