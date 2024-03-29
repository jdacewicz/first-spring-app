package pl.jdacewicz.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdacewicz.socialmedia.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
