package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import org.springframework.stereotype.Controller;

@Controller
public class HashtagController {
    private HashtagStorage hashtagStorage;

    public HashtagController(HashtagStorage hashtagStorage) {
        this.hashtagStorage = hashtagStorage;
    }




}
