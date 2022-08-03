package com.brainstation.usisclone.repository;
import com.brainstation.usisclone.models.jwt.JwtToken;
import com.brainstation.usisclone.models.user.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long>{
    JwtToken findByToken(String token);
}






