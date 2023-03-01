package pl.jdacewicz.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.Reaction;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.repository.PostRepository;
import pl.jdacewicz.socialmedia.repository.ReactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private ReactionRepository reactionRepository;

    @Autowired
    public PostService(PostRepository postRepository, ReactionRepository reactionRepository) {
        this.postRepository = postRepository;
        this.reactionRepository = reactionRepository;
    }

    public Post createPost(Post post) {
        List<Reaction> reactions = reactionRepository.findAll();

        post.setReactionCounters(reactions.stream()
                .map(r -> new ReactionCounter(r))
                .toList());
        postRepository.save(post);
        return post;
    }

    public List<Post> getRandomPosts() {
        return postRepository.getRandomPosts();
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPosts(User user) {
        return postRepository.findByPostCreator(user);
    }

    public void updatePost(Post post) {
        postRepository.saveAndFlush(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
        postRepository.flush();
    }
}
