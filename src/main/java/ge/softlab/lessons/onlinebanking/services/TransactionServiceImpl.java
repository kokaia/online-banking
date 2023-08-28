package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.entities.TransactionDomain;
import ge.softlab.lessons.onlinebanking.models.TransactionAddModel;
import ge.softlab.lessons.onlinebanking.repositories.AccountRepository;
import ge.softlab.lessons.onlinebanking.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public void addTransaction(TransactionAddModel data) {
        var transaction = new TransactionDomain();

        if (Objects.equals(data.creditAccountId(), data.debitAccountId())){
            throw new RuntimeException("არ შეიძლება ერთიდაიგივე ანგარიშებს შორის გადარცხვა");
        }

        var credit = accountRepository.findById(data.creditAccountId()).orElseThrow();
        if (Objects.equals(credit.getBlocked(), Boolean.TRUE)){
            throw new RuntimeException("account_is_blocked");
        }

        var debit = accountRepository.findById(data.debitAccountId()).orElseThrow();
        if (Objects.equals(debit.getBlocked(), Boolean.TRUE)){
            throw new RuntimeException("account_is_blocked");
        }

        if (credit.getAmount() - data.amount() < 0){
            throw new RuntimeException("თანხა არაა საკმარისი ანგარიშზე");
        }

        transaction.setDebitAccount(debit);
        transaction.setCreditAccount(credit);
        transaction.setAmount(data.amount());
        transaction.setComment(data.comment());
        transactionRepository.save(transaction);

    }
}
