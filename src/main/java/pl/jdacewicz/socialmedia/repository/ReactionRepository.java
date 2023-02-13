package pl.jdacewicz.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdacewicz.socialmedia.domain.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
