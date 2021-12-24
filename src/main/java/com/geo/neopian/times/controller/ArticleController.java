package com.geo.neopian.times.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.geo.neopian.times.entry.NTEntry;
import com.geo.neopian.times.parser.impl.EntryListParser;

@RestController
public class ArticleController {

    @GetMapping("/")
    public String index() {
        return "Welcome to my Neopian Times retriever service.";
    }

    @GetMapping("articles/{issue}/")
    public ResponseEntity<List<NTEntry>> getArticlesByIssue(@PathVariable String issue) {

        final EntryListParser entryListParser = new EntryListParser();
        HttpURLConnection httpcon = null;
        try {

            final URL url = new URL("http://www.neopets.com/ntimes/index.phtml?section=articles&week=" + issue);
            httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

            try(final InputStream inputStream = httpcon.getInputStream()){
                if (inputStream == null) {
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .build();
                }
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(entryListParser.parse(inputStream));
            } finally {
                if (httpcon != null) {
                    httpcon.disconnect();
                }
            }
        } catch (final IOException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
