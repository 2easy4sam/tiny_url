package com.big4.services;

public interface TinyUrlService {
    String encode(String longUrl);

    String decode(String shortUrl);
}
