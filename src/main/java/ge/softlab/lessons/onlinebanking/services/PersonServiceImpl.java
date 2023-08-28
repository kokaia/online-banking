package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.entities.Account;
import ge.softlab.lessons.onlinebanking.entities.Person;
import ge.softlab.lessons.onlinebanking.models.AccountCreateModel;
import ge.softlab.lessons.onlinebanking.models.PersonSearchModel;
import ge.softlab.lessons.onlinebanking.repositories.AccountRepository;
import ge.softlab.lessons.onlinebanking.repositories.PersonRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Person> search(PersonSearchModel params) {
        Page<Person> ans = personRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotEmpty(params.personalNumber())){
                predicates.add(criteriaBuilder.like( root.get("personalNumber"), "%" + params.personalNumber() + "%"));
            }
            if (StringUtils.isNotEmpty(params.firstName())){
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + params.firstName() + "%"));
            }
            if (StringUtils.isNoneEmpty(params.lastName())){
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + params.lastName() + "%"));
            }
            if (params.ageFrom() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthDate"), LocalDate.now().minusYears(params.ageFrom())));
            }
            if (params.ageTo() != null){
                predicates.add(criteriaBuilder.greaterThan(root.get("birthDate"), LocalDate.now().minusYears(params.ageTo())));
            }
            if (StringUtils.isNotEmpty(params.iban())){
                Subquery<Integer> sub = query.subquery(Integer.class);
                Root<Account> accountRoot = sub.from(Account.class);
                sub.select(accountRoot.get("personId"));
                sub.where(criteriaBuilder.like(accountRoot.get("iban"), "%" + params.iban() + "%"));
                predicates.add(criteriaBuilder.in(root.get("id")).value(sub));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }, Pageable.ofSize(10));

        return ans.stream().toList();
    }

    @Override
    public Optional<Person> getPerson(Integer id){
        if (personRepository.existsById(id)){
            return personRepository.findById(id);
        }
        return Optional.empty();
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
