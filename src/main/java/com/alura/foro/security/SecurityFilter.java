package com.alura.foro.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = cleanToken(request);

        if (token != null && tokenService.getSubject(token)!= null ){
            try{
                String userName = tokenService.getSubject(token);
                UserDetails userDetails = service.loadUserByUsername(userName);

                if (userDetails != null){
                    Authentication auth  = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e){
                SecurityContextHolder.clearContext();
                throw new RuntimeException("Token invalido" , e);
            }

        }

        filterChain.doFilter(request,response);

    }


    public String cleanToken ( HttpServletRequest request){
        String tokenBearer = request.getHeader("Authorization");
        if (tokenBearer != null && tokenBearer.startsWith("Bearer ")){
            return tokenBearer.replace("Bearer ","");
        }
        return null;
    }
}





