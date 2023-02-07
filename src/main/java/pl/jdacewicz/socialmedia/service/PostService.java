package pl.jdacewicz.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.domain.repo.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(Post post) {
        postRepository.save(post);
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
}
