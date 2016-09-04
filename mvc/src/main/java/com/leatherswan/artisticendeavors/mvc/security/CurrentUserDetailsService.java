
package com.leatherswan.artisticendeavors.mvc.security;

import com.leatherswan.artisticendeavors.jpa.model.CurrentUser;
import com.leatherswan.artisticendeavors.jpa.model.User;
import com.leatherswan.artisticendeavors.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Autowired
    public CurrentUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }
        return new CurrentUser(user);
    }

}
