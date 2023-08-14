package ge.softlab.lessons.onlinebanking.controllers;

import ge.softlab.lessons.onlinebanking.entities.Account;
import ge.softlab.lessons.onlinebanking.entities.Person;
import ge.softlab.lessons.onlinebanking.models.AccountCreateModel;
import ge.softlab.lessons.onlinebanking.models.PersonModel;
import ge.softlab.lessons.onlinebanking.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<PersonModel> search(String firstName, String lastName, String personalNumber){
        var persons = personService.search(personalNumber, firstName, lastName);
        var ans = new ArrayList<PersonModel>();
        for (var person: persons) {
            ans.add(new PersonModel(person.getId(), person.getPersonalNumber(), person.getFirstName(), person.getLastName(), person.getBirthDate()));
        }
        return ans;
    }

    @GetMapping("{id}")
    public Person person(@PathVariable Integer id){
        return personService.getPerson(id);
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
