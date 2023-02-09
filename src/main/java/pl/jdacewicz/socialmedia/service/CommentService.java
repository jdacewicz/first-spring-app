package pl.jdacewicz.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmedia.domain.Comment;
import pl.jdacewicz.socialmedia.domain.repo.CommentRepository;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(Comment comment) {
        commentRepository.saveAndFlush(comment);
    }
}
