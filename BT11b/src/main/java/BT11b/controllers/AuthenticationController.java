package BT11b.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import BT11b.entity.User;
import BT11b.models.LoginResponse;
import BT11b.models.LoginUserModel;
import BT11b.models.RegisterUserModel;
import BT11b.services.AuthenticationService;
import BT11b.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService
    ) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    // API đăng ký user mới
    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<User> register(@RequestBody RegisterUserModel registerUser) {
        User registeredUser = authenticationService.signup(registerUser);
        return ResponseEntity.ok(registeredUser);
    }

    // API login trả về JWT
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUser) {
        User authenticatedUser = authenticationService.authenticate(loginUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
