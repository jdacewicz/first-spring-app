package pl.jdacewicz.socialmedia.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdacewicz.socialmedia.domain.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
