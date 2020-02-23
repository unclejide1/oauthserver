package com.jide.oauthserver.service;

import com.jide.oauthserver.model.AuthUserDetail;
import com.jide.oauthserver.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.jide.oauthserver.model.User;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    private UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userDetailRepository.findByUsername(username);

        user.orElseThrow(()->  new  UsernameNotFoundException("Username or password wrong"));


        UserDetails userDetails = new AuthUserDetail(user.get());

        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;

    }
}
