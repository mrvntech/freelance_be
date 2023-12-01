package com.example.freelance_be.security;

import com.example.freelance_be.entities.User;
import com.example.freelance_be.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isEmpty()) return null;
        UserDetailImpl userDetail = new UserDetailImpl();
        userDetail.setId(user.get().getId());
        userDetail.setUsername(user.get().getEmail());
        userDetail.setPassword(user.get().getPassword());
        Collection<? extends GrantedAuthority> authorities = user.get().getRoles().stream().map(role -> (GrantedAuthority) role::getName).toList();
        userDetail.setAuthorities(authorities);
        return userDetail;
    }
}
