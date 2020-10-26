package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HashtagController {

    private HashtagStorage hashtagStorage;

    public HashtagController(HashtagStorage hashtagStorage) {
        this.hashtagStorage = hashtagStorage;
    }


    @GetMapping("/hashtag/{id}")
    public String displaySingleHashtag(Model model, @PathVariable Long id){
        model.addAttribute("hashtag", hashtagStorage.retrieveHashtagById(id));

        return "single-hashtag-template";
    }

    @GetMapping("/hashtags")
    public String displayAllHashtags(Model model){
        model.addAttribute("hashtags", hashtagStorage.retrieveAllHashtags());

        return "all-hashtag-template";
    }
    @PostMapping("/post")
    public String addHashtag(@RequestParam String name){
        Hashtag hashtagToAdd = new Hashtag(name);
        hashtagStorage.save(hashtagToAdd);

        return "redirect:/post";
    }



}
