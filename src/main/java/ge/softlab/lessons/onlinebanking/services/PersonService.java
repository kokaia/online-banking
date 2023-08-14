package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.entities.Account;
import ge.softlab.lessons.onlinebanking.entities.Person;
import ge.softlab.lessons.onlinebanking.models.AccountCreateModel;

import java.util.List;

public interface PersonService {

    List<Person> search(String personalNumber, String firstName, String lastName);
    Person getPerson(Integer id);

    List<Account> getPersonAccounts(Integer id);

    Account personCreateAccount(Integer personId, AccountCreateModel accountCreateModel);
}
