package arpad.bank.bankeventstore.repository;

import arpad.bank.bankeventstore.enitys.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<Event, Long> {
}
