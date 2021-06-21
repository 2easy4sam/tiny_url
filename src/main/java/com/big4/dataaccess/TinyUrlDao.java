package com.big4.dataaccess;

public interface TinyUrlDao {
    String getLongUrl(String shortUrl);

    void saveShortUrl(String shortUrl);
}
