package com.officerental.rental.Services;


import com.officerental.rental.models.User;

public interface IUserService {
    User registerUser(User user);
    User loginUser(String email, String password);
}
