package com.getir.readingisgood.model.entity;

import com.getir.readingisgood.model.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "Tokens",timeToLive = 2592000)
public class TokenEntity implements Serializable {
  @Id private String token;
  private Long customerId;
  private TokenType tokenType = TokenType.BEARER;
  private boolean revoked;
  private boolean expired;
}
