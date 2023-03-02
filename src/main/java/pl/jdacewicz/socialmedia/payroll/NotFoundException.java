package pl.jdacewicz.socialmedia.payroll;

public class NotFoundException extends RuntimeException{

    public NotFoundException(Long id) {
        super("Could not find record with id = " + id);
    }
}
