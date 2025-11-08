package com.retromarket.core.service.jwt;


public interface JwtService {
  String generate(final String userName);
  //Boolean validate(String token, UserDetails userDetails);
  Boolean isTokenExpired(String token);
  String extractUsername(String token);
}
