package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.models.TransactionAddModel;
import jakarta.validation.Valid;

public interface TransactionService {

    void addTransaction(TransactionAddModel data);

}
