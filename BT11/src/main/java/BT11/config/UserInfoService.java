package BT11.config;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import BT11.entity.UserInfo;
import BT11.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;

    // Constructor injection
    public UserInfoService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
