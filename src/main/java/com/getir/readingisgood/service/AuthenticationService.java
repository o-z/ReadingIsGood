package com.getir.readingisgood.service;

import com.getir.readingisgood.configuration.security.JwtService;
import com.getir.readingisgood.model.dto.AuthenticationResponse;
import com.getir.readingisgood.model.entity.CustomerEntity;
import com.getir.readingisgood.model.entity.TokenEntity;
import com.getir.readingisgood.model.enums.Role;
import com.getir.readingisgood.model.enums.TokenType;
import com.getir.readingisgood.model.request.AuthenticationRequest;
import com.getir.readingisgood.model.request.RegisterRequest;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
  private final CustomerRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    CustomerEntity customerEntity =
        CustomerEntity.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    CustomerEntity savedCustomerEntity = repository.save(customerEntity);
    var jwtToken = jwtService.generateToken(customerEntity);
    saveUserToken(savedCustomerEntity.getId(), jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    CustomerEntity customerEntity = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(customerEntity);
    revokeAllUserTokens(customerEntity);
    saveUserToken(customerEntity.getId(), jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  private void saveUserToken(Long customerId, String jwtToken) {
    var token =
        TokenEntity.builder()
            .customerId(customerId)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(CustomerEntity customerEntity) {
    var validUserTokens =
        tokenRepository.findByCustomerIdAndExpiredAndRevoked(
            customerEntity.getId(), false, false);
    if (validUserTokens.isEmpty()) return;
    validUserTokens.forEach(
        token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });
    tokenRepository.saveAll(validUserTokens);
  }
}
