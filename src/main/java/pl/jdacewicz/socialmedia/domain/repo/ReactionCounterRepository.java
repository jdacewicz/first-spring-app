package pl.jdacewicz.socialmedia.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;

public interface ReactionCounterRepository extends JpaRepository<ReactionCounter, Long> {
}
