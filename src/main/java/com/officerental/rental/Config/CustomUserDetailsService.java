package com.officerental.rental.Config;



import com.officerental.rental.Reposetories.UserRepository;
import com.officerental.rental.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByEmail(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("User with email '" + username + "' not found");
        }

        // Determine role based on 'admin' in name or email
        String role = (appUser.getName().toLowerCase().contains("admin") ||
                appUser.getEmail().toLowerCase().contains("admin"))
                ? "ROLE_ADMIN"
                : "ROLE_USER";

        return new org.springframework.security.core.userdetails.User(
                appUser.getName(),             // Use email as username
                appUser.getPassword(),          // Password should be BCrypt encoded
                true,                           // Account is enabled
                true,                           // Account is non-expired
                true,                           // Credentials are non-expired
                true,                           // Account is non-locked
                Collections.singleton(new SimpleGrantedAuthority(role)) // Set role
        );
    }

}

