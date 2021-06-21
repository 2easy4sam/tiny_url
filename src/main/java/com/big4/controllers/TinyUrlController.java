package com.big4.controllers;

import com.big4.services.TinyUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiny_url")
public class TinyUrlController {

    private final TinyUrlService tinyUrlService;

    public TinyUrlController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @RequestMapping(value = "/decode", method = RequestMethod.GET)
    public ResponseEntity<String> decode(@RequestParam("short_url") String shortUrl) {
        String longUrl = tinyUrlService.decode(shortUrl);
        return new ResponseEntity<>(longUrl, HttpStatus.OK);
    }

    @RequestMapping(value = "/encode", method = RequestMethod.POST)
    public ResponseEntity<String> encode(@RequestBody String longUrl) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
