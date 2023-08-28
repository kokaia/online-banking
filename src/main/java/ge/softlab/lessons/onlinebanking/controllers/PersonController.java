package ge.softlab.lessons.onlinebanking.controllers;

import ge.softlab.lessons.onlinebanking.entities.Account;
import ge.softlab.lessons.onlinebanking.entities.Person;
import ge.softlab.lessons.onlinebanking.models.AccountCreateModel;
import ge.softlab.lessons.onlinebanking.models.PersonSearchModel;
import ge.softlab.lessons.onlinebanking.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> search(PersonSearchModel params){
        var persons = personService.search(params);
//        var ans = new ArrayList<PersonModel>();
//        for (var person: persons) {
//            ans.add(new PersonModel(person.getId(), person.getPersonalNumber(), person.getFirstName(), person.getLastName(), person.getBirthDate()));
//        }
        return persons;
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> person(@PathVariable Integer id){
        return ResponseEntity.of(personService.getPerson(id));
    }

    @GetMapping("{personId}/accounts")
    public List<Account> personAccounts(@PathVariable Integer personId){
        return personService.getPersonAccounts(personId);
    }

    @PostMapping("{personId}/accounts")
    public Account personCreateAccount(@PathVariable Integer personId, @RequestBody AccountCreateModel accountCreateModel){
        return personService.personCreateAccount(personId, accountCreateModel);
    }

}
