package pl.jdacewicz.socialmedia.service.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.jdacewicz.socialmedia.domain.User;

import java.util.Collection;
import java.util.List;

public class DBUserPrincipal implements UserDetails {

    private User user;

    public DBUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO Not implemented.
        return List.of(() -> "USER");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO Not implemented.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO Not implemented.
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO Not implemented.
        return true;
    }
}
