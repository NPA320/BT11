// BT11b/services/CustomUserDetailsService.java
package BT11b.services;

import BT11b.entity.User;
import BT11b.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username ở đây là email
        return repo.findByEmail(username)
                   .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
