package com.recontract.recontract.service;

import com.recontract.recontract.domain.ERole;
import com.recontract.recontract.domain.Role;
import com.recontract.recontract.domain.User;
import com.recontract.recontract.repository.RoleRepository;
import com.recontract.recontract.repository.UserRepository;
import com.recontract.recontract.service.security.jwt.JwtUtils;
import com.recontract.recontract.payload.request.LoginRequest;
import com.recontract.recontract.payload.request.SignupRequest;
import com.recontract.recontract.payload.response.JwtResponse;
import com.recontract.recontract.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthorizationService {

    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<MessageResponse> registerUser(@Valid SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getHiringOrFreelancer());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }
}
