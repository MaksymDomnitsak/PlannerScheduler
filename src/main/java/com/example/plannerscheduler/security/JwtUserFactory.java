package com.example.plannerscheduler.security;

import com.example.plannerscheduler.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getUserRole().name())
        );
    }

    private static GrantedAuthority mapToGrantedAuthorities(String userRole) {
        return new SimpleGrantedAuthority(userRole);
    }
}