package com.officerental.rental.Services;


import com.officerental.rental.Additions.PasswordUtils;
import com.officerental.rental.Reposetories.UserRepository;
import com.officerental.rental.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;




    @Override
    public User registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Email is already registered");
        }

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);



        // Save the user to the database
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        User optionalUser = userRepository.findByEmail(email);
        if (optionalUser == null) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser;

        // Verify the password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
