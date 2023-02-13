package pl.jdacewicz.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;

public interface ReactionCounterRepository extends JpaRepository<ReactionCounter, Long> {
}
