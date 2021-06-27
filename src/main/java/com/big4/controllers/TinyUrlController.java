package com.big4.controllers;

import com.big4.dtos.EncodeRequest;
import com.big4.services.TinyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiny_url")
public class TinyUrlController {

    private final TinyUrlService tinyUrlService;

    @Autowired
    public TinyUrlController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @RequestMapping(value = "/decode", method = RequestMethod.GET)
    public ResponseEntity<String> decode(@RequestParam("short_url") String shortUrl) {
        String longUrl = tinyUrlService.decode(shortUrl);
        return new ResponseEntity<>(longUrl, HttpStatus.OK);
    }

    @RequestMapping(value = "/encode", method = RequestMethod.POST)
    public ResponseEntity<String> encode(@RequestBody EncodeRequest request) {
        String shortUrl = tinyUrlService.encode(request.getLongUrl());
        return ResponseEntity.ok(shortUrl);
    }
}
