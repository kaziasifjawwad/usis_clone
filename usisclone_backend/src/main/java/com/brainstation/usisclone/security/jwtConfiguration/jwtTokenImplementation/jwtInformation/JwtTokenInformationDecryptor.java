package com.brainstation.usisclone.security.jwtConfiguration.jwtTokenImplementation.jwtInformation;

import org.springframework.security.core.context.SecurityContextHolder;

public class JwtTokenInformationDecryptor {

    public String getUserName() {
        String username = null;
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            username = authentication.getName();
        }
        return username;
    }
}
