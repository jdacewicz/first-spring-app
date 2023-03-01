package pl.jdacewicz.socialmedia.payroll;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(Long id) {
        super("Could not find post with id = " + id);
    }
}
