package ge.softlab.lessons.onlinebanking.repositories;

import ge.softlab.lessons.onlinebanking.entities.TransactionDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDomain, Integer> {

}
