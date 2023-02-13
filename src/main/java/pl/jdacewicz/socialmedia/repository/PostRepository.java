package pl.jdacewicz.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("FROM Post p ORDER BY random() LIMIT 10")
    List<Post> getRandomPosts();
    List<Post> findByPostCreator(User user);
}
