package com.big4.dataaccess;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTinyUrlDao implements TinyUrlDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTinyUrlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getLongUrl(String shortUrl) {
        return null;
    }

    @Override
    public void saveShortUrl(String shortUrl) {

    }
}