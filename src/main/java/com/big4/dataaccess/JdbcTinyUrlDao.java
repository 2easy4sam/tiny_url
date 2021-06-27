package com.big4.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class JdbcTinyUrlDao implements TinyUrlDao {

    private static final String SELECT_LONG_URL_BY_SHORT_URL = "SELECT * FROM lookup WHERE short_url = ?";
    private static final String INSERT_SHORT_URL = "INSERT INTO lookup (short_url, long_url, created_at) VALUES (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTinyUrlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getLongUrl(String shortUrl) {
        final List<String> list = jdbcTemplate.query(SELECT_LONG_URL_BY_SHORT_URL, (rs, rowNum) -> {
            ResultSetMetaData metaData = rs.getMetaData();
            final int colCnt = metaData.getColumnCount();

            for (int i = 1; i <= colCnt; i++) {
                if (metaData.getColumnName(i).equals("long_url")) {
                    return rs.getString("long_url");
                }
            }

            return null;
        }, shortUrl);

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean saveShortUrl(String shortUrl, String longUrl) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return jdbcTemplate.update(INSERT_SHORT_URL, shortUrl, longUrl, sdf.format(new Date())) == 1;
    }
}