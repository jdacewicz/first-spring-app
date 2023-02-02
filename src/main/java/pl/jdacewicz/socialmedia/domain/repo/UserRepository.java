package pl.jdacewicz.socialmedia.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdacewicz.socialmedia.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
