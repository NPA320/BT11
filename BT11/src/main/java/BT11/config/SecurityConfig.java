package BT11.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import BT11.repository.UserInfoRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 
public class SecurityConfig {

	@Autowired
	UserInfoRepository repository;
	
	@Bean 
	UserDetailsService userDetailsService()
	{
		return new UserInfoService(repository);
		
	}
	
	
	@Bean
	AuthenticationProvider authenticationProvider(){
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	authenticationProvider.setUserDetailsService (userDetailsService());
	authenticationProvider.setPasswordEncoder (passwordEncoder());
	return authenticationProvider;
	} 	
	
//    // Authentication
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails admin = User.withUsername("trung")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    	return http.csrf(csrf -> csrf.disable())
    	.authorizeHttpRequests (auth -> auth
    	.requestMatchers("/user/new").permitAll()
    	.requestMatchers("/").permitAll()
    	.requestMatchers("/customer/**").authenticated()
    	//.anyRequest().authenticated()
    	)
    	.formLogin(Customizer.withDefaults())
    	.build();
    	}
}
