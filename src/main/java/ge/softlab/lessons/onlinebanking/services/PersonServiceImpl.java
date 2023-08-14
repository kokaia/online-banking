package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.entities.Account;
import ge.softlab.lessons.onlinebanking.entities.Person;
import ge.softlab.lessons.onlinebanking.models.AccountCreateModel;
import ge.softlab.lessons.onlinebanking.repositories.AccountRepository;
import ge.softlab.lessons.onlinebanking.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Person> search(String personalNumber, String firstName, String lastName) {
        if (personalNumber == null){
            personalNumber = "";
        }
        if (firstName == null){
            firstName = "";
        }
        if (lastName == null){
            lastName = "";
        }
        return personRepository.findPersons("%" + personalNumber + "%", "%" + firstName + "%", "%" + lastName + "%");
    }

    @Override
    public Person getPerson(Integer id){
        return personRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Account> getPersonAccounts(Integer id) {
        return accountRepository.findAllByPersonId(id);
    }

    @Override
    public Account personCreateAccount(Integer personId, AccountCreateModel accountCreateModel) {
        if (!personRepository.existsById(personId)){
            throw new RuntimeException("person with id " + personId + " does not exists");
        }
        Account account = new Account();
        account.setPersonId(personId);
        account.setName(accountCreateModel.name());
        account.setIban(accountCreateModel.iban());
        account.setAmount(0.0);
        accountRepository.save(account);
        return account;
    }


}
