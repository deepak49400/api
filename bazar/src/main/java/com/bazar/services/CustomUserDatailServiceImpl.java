package com.bazar.services;

import com.bazar.model.CustomUserDetail;
import com.bazar.model.User;
import com.bazar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDatailServiceImpl implements CustomUserDatailService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
         user.orElseThrow(()->new UsernameNotFoundException("User is not found"));
        return user.map(CustomUserDetail :: new).get();
    }
}
