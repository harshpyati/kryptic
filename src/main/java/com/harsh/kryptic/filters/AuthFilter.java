package com.harsh.kryptic.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.kryptic.domain.user.UserInfo;
import com.harsh.kryptic.exceptions.InternalServerException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.harsh.kryptic.utils.AuthConstants.*;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authManager;

    public AuthFilter(AuthenticationManager authenticationManager) {
        this.authManager = authenticationManager;
        setFilterProcessesUrl(LOGIN_URL);
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserInfo userCredentials = new ObjectMapper().readValue(request.getInputStream(), UserInfo.class);
            System.out.println("username :: " + userCredentials.getUsername() + " :: " + userCredentials.getPassword()) ;
            return authManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userCredentials.getUsername(),
                                    userCredentials.getPassword()
                            )
                    );
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName = ((User) authResult.getPrincipal()).getUsername();
        String accessToken = JWT
                .create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET));

        UserInfo loggedInUser = new UserInfo();
        loggedInUser.setUsername(userName);
        loggedInUser.setAccessToken(accessToken);

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        String json = objectMapper.writeValueAsString(loggedInUser);
        response.getWriter().write(json);
    }
}
