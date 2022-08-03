package com.brainstation.usisclone.controller;

import com.brainstation.usisclone.models.user.DAOUser;
import com.brainstation.usisclone.payload.AuthenticateRequest;
import com.brainstation.usisclone.payload.AuthenticationResponse;
import com.brainstation.usisclone.security.jwtTokenImplementation.jwtConfiguration.JwtTokenUtil;
import com.brainstation.usisclone.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/users")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


//    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody DAOUser user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

}