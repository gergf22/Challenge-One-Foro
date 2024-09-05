package com.alura.foro.security;

import com.alura.foro.modelo.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;


@Service
public class TokenService {


    public String generateToken(Usuario usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("foro-Gergf22")
                    .withSubject(usuario.getUsername())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);


        } catch (JWTCreationException e){

            throw new RuntimeException("Error al generar el token", e);
        }
    }




    public String getSubject(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("foro-Gergf22")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            return decodedJWT.getSubject();

        }catch (JWTVerificationException e){
            throw new RuntimeException("Token invalido", e);
        }

    }


    private Instant getExpirationDate (){
        Instant now = Instant.now();

        return now.plus(Duration.ofHours(24));
    }



    public Boolean validateToken (String token){
        if (token !=null && getSubject(token)!= null){
            return true;
        }
        return false;

    }
}


