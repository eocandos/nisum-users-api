package com.nisum.users.api.controller;

import com.nisum.users.api.entity.User;
import com.nisum.users.api.service.UserService;
import com.nisum.users.api.utils.PasswordValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
// @RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("user")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping("user")
    public ResponseEntity create(@RequestBody User user) {

        if(!isValidEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("¡Correo incorrecto!");
        }
        if(!PasswordValidator.isValid(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Favor validar el formato de la contraseña");
        }

        try {
            Optional<User> u = userService.findByEmail(user.getEmail());
            if(!u.isPresent()){
                String token = getJWTToken(user.getEmail());
                user.setToken(token);
                userService.save(user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("¡Correo ya registrado!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No fue posible registrar el usuario");
        }

    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
