package com.example.plannerscheduler.security.auth;

import com.example.plannerscheduler.exception.TokenRefreshException;
import com.example.plannerscheduler.repository.UserRepository;
import com.example.plannerscheduler.security.token.Token;
import com.example.plannerscheduler.security.token.TokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
  @Value("${PlannerScheduler.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private TokenRepository refreshTokenRepository;

  @Autowired
  private UserRepository userRepository;

  public Optional<Token> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public Token createRefreshToken(Long userId) {
    Token refreshToken = new Token();

    refreshToken.setUser(userRepository.findById(userId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public Token verifyExpiration(Token token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }

}
