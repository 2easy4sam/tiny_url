package com.big4.dataaccess;

public interface TinyUrlDao {
    String getLongUrl(String shortUrl);

    boolean saveShortUrl(String shortUrl, String longUrl);
}
