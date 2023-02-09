package pl.jdacewicz.socialmedia.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdacewicz.socialmedia.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
