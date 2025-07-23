package br.com.emersonpesoa.picpay_desafio_backend.transaction;

import org.springframework.stereotype.Service;

import br.com.emersonpesoa.picpay_desafio_backend.wallet.TransactionRepository;
import br.com.emersonpesoa.picpay_desafio_backend.wallet.Wallet;
import br.com.emersonpesoa.picpay_desafio_backend.wallet.WalletType;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionService(TransactionRepository transactionRepository,
            WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        // 1- Validar as tansações com base na regra de
        validate(transaction);
        // 2 - Criar transação
        var newTransaction = transactionRepository.save(transaction);

        // 3 - Debitar da carteira
        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));

        // 4 - Chamar serviços
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
                        .orElseThrow())
                .orElseThrow();

    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue()
                && payer.balance().compareTo(transaction.value()) >= 0
                && !payer.id().equals(transaction.payee());
    }
}
