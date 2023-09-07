package ge.softlab.lessons.onlinebanking.controllers;

import ge.softlab.lessons.onlinebanking.entities.Account;
import ge.softlab.lessons.onlinebanking.entities.Person;
import ge.softlab.lessons.onlinebanking.models.AccountCreateModel;
import ge.softlab.lessons.onlinebanking.models.PersonModel;
import ge.softlab.lessons.onlinebanking.models.PersonSearchModel;
import ge.softlab.lessons.onlinebanking.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("persons")
public class PersonController {

    private final PersonService personService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<PersonModel> search(PersonSearchModel params){
        return personService.search(params).map(Person::toPersonModel);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<Person> person(@PathVariable Integer id){
        return ResponseEntity.of(personService.getPerson(id));
    }

    @GetMapping("{personId}/accounts")
    public List<Account> personAccounts(@PathVariable Integer personId){
        return personService.getPersonAccounts(personId);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("{personId}/accounts")
    public Account personCreateAccount(@PathVariable Integer personId, @RequestBody AccountCreateModel accountCreateModel){
        return personService.personCreateAccount(personId, accountCreateModel);
    }

}
