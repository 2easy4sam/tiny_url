package com.big4.services;

import com.big4.dataaccess.TinyUrlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {

    private final TinyUrlDao tinyUrlDao;

    @Autowired
    public TinyUrlServiceImpl(TinyUrlDao tinyUrlDao) {
        this.tinyUrlDao = tinyUrlDao;
    }

    @Override
    public String encode(String longUrl) {
        return encodeLongUrl(longUrl);
    }

    @Override
    public String decode(String shortUrl) {
        return tinyUrlDao.getLongUrl(shortUrl);
    }

    private String encodeLongUrl(String longUrl) {
        String shortUrl;

        while (tinyUrlDao.getLongUrl(shortUrl = getRandomAlphaNumericString()) != null) ;

        tinyUrlDao.saveShortUrl(shortUrl, longUrl);

        return shortUrl;
    }

    private String getRandomAlphaNumericString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(uuid.length() - 7);
    }
}
