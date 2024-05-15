package com.example.pi_dev_4eme__poker_planning.Services;


import com.example.pi_dev_4eme__poker_planning.Api.sendRegistrationEmail;
import com.example.pi_dev_4eme__poker_planning.Controllers.AuthenticationRequest;
import com.example.pi_dev_4eme__poker_planning.Controllers.AuthenticationResponse;
import com.example.pi_dev_4eme__poker_planning.Controllers.RegisterRequest;
import com.example.pi_dev_4eme__poker_planning.Entities.StatusUser;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final sendRegistrationEmail sE;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .Nom(request.getNom())
                .Prenom(request.getPrenom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rolee(request.getRole())
                .Status(StatusUser.active) // Set status to active

                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        // Sending email to the user
        sE.RegistrationEmail(request,user.getEmail(), request.getPassword());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /*public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        System.out.println(user);
        System.out.println(jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }*/
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Retrieve user from repository
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

            // Generate JWT token
            String jwtToken = jwtService.generateToken(user);

            // Create AuthenticationResponse object with user properties
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .idUser(user.getIdUser())
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .photo(user.getPhoto())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .rolee(user.getRolee())
                    .status(user.getStatus())
                    .tel(user.getTel())

                    .build();
        } catch (AuthenticationException ex) {
            // Handle authentication failure
            throw new UsernameNotFoundException("Incorrect email or password");
        }
    }

}