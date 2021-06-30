package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.model.Person;
import com.codecool.movingmotivators.model.UserCredentials;
import com.codecool.movingmotivators.security.CustomUser;
import com.codecool.movingmotivators.security.jwt.JwtService;
import com.codecool.movingmotivators.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    private final PersonService personService;

    private final AuthenticationManager authenticationManager;

    private final String TOKEN_FIELD_NAME = "token";
    private final String TOKEN_BEARER = "Bearer";
    private final String TOKEN_PATH = "/";

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserCredentials data, HttpServletResponse res) {
        try {
            String username = data.getUsername();
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            List<String> roles = auth.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            CustomUser user = (CustomUser) auth.getPrincipal();
            Person person = personService.getUser(username).orElse(null);

            if (person == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            user.setId(person.getId());

            String token = jwtService.createToken(user.getId(), username, roles);

            Cookie cookie = new  Cookie(TOKEN_FIELD_NAME, TOKEN_BEARER + token);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath(TOKEN_PATH);

            res.addCookie(cookie);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest req, HttpServletResponse res) {
        Cookie cookie = new Cookie(TOKEN_FIELD_NAME, null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath(TOKEN_PATH);

        res.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}
