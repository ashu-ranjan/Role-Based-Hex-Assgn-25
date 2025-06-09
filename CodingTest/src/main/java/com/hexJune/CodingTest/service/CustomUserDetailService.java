package com.hexJune.CodingTest.service;

import com.hexJune.CodingTest.model.User;
import com.hexJune.CodingTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch User by given username
        User user = userRepository.getByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Invalid Credentials");

        // Convert your Role into Authority as spring works with authority
        SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole());

        // Add this SimpleGrantedAuthority Object (sga) into the List now
        List<GrantedAuthority> list = List.of(sga);

        // Convert our User to spring's User that is UserDetails
        org.springframework.security.core.userdetails.User springUser =
                new org.springframework.security.core.userdetails.User
                        (user.getUsername(),
                                user.getPassword(),
                                list);
        return springUser;
    }
}
