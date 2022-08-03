package com.brainstation.usisclone.controller;

import com.brainstation.usisclone.models.jwt.JwtToken;
import com.brainstation.usisclone.models.user.DAOUser;
import com.brainstation.usisclone.payload.AuthenticateRequest;
import com.brainstation.usisclone.payload.AuthenticationResponse;
import com.brainstation.usisclone.security.jwtTokenImplementation.jwtConfiguration.JwtTokenUtil;
import com.brainstation.usisclone.services.CustomUserDetailsService;
import com.brainstation.usisclone.services.JwtService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "api/users")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    JwtService jwtService;


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

    @PostMapping("/blacklist")
    public ResponseEntity<?> blackListUser(@RequestBody JwtToken token) throws Exception {
        return ResponseEntity.ok(jwtService.blackListJwtToken(token));
    }

    @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        System.out.println("hiiii");
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }


}