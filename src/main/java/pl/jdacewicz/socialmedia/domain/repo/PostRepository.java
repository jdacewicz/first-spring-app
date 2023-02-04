package pl.jdacewicz.socialmedia.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.jdacewicz.socialmedia.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("FROM Post p ORDER BY random() LIMIT 10")
    List<Post> getRandomPosts();
}
