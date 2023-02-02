package pl.jdacewicz.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.domain.repo.UserRepository;
import pl.jdacewicz.socialmedia.service.principal.DBUserPrincipal;

@Service
@Qualifier("DB")
public class DBUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public DBUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        return new DBUserPrincipal(user);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
