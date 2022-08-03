package com.brainstation.usisclone.services;
import com.brainstation.usisclone.models.jwt.JwtToken;
import com.brainstation.usisclone.repository.JwtTokenRepository;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    public JwtToken blackListJwtToken(JwtToken token){
        JwtToken tokenCheck = jwtTokenRepository.findByToken(token.getToken());
        System.out.println(tokenCheck);
        if(tokenCheck ==null){
            return  jwtTokenRepository.save(token);
        }
        return tokenCheck;
    }

}