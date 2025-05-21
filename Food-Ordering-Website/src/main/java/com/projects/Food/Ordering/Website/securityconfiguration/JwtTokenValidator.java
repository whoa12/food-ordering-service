package com.projects.Food.Ordering.Website.securityconfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String jwt = request.getHeader(JwtConstant.Jwt_Header);

         if(jwt!=null && jwt.startsWith("Bearer ")) {
             jwt = jwt.substring(7);

             try {
                 SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                 Claims claims = Jwts.parser()
                         .verifyWith(key)
                         .build()
                         .parseSignedClaims(jwt)
                         .getPayload();
                 System.out.println(claims);

                 String email = String.valueOf(claims.get("email"));
                 String authorities = String.valueOf(claims.get("authorities"));
                 List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                 Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
                 SecurityContextHolder.getContext().setAuthentication(authentication);
                 System.out.println("Authorities: "+ authorities);
                 System.out.println("Authority list: "+ auth);

             } catch (Exception e) {
                 throw new BadCredentialsException("invalid token");
             }
         }
        ;
         filterChain.doFilter(request, response);

    }
}
