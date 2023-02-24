package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.entity.TokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, String> {
  List<TokenEntity> findByCustomerIdAndExpiredAndRevoked(
      Long customerId, Boolean expired, Boolean revoked);
}
