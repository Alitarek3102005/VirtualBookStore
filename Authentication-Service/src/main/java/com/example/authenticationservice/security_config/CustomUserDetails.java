package com.example.authenticationservice.security_config;

import com.example.authenticationservice.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.example.authenticationservice.entity.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        // Role
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        // Default permission (لكل الناس)
        authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGES"));

        // Admin
        if (user.getRole() == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGES"));
            authorities.add(new SimpleGrantedAuthority("DELETE_PRIVILEGES"));
        }

        // Publisher
        if (user.getRole() == Role.PUBLISHER) {
            authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGES"));
        }

        return authorities;
    }

    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getUsername(); }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return user.isEnabled(); }
}
