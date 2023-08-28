package ge.softlab.lessons.onlinebanking.repositories;

import ge.softlab.lessons.onlinebanking.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

    @Query("select p from Person p where p.personalNumber like :personalNumber and p.firstName like :firstName and p.lastName like :lastName order by p.id desc")
    List<Person> findPersons(@Param("personalNumber") String personalNumber, @Param("firstName") String firstName, @Param("lastName") String lastName);

}
