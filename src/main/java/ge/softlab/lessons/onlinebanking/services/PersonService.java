package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.entities.Account;
import ge.softlab.lessons.onlinebanking.entities.Person;
import ge.softlab.lessons.onlinebanking.models.AccountCreateModel;
import ge.softlab.lessons.onlinebanking.models.PersonSearchModel;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> search(PersonSearchModel params);
    Optional<Person> getPerson(Integer id);

    List<Account> getPersonAccounts(Integer id);

    Account personCreateAccount(Integer personId, AccountCreateModel accountCreateModel);
}
