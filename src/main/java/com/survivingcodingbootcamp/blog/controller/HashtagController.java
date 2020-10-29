package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HashtagController {

    private HashtagStorage hashtagStorage;

    private PostStorage postStorage;

    public HashtagController(HashtagStorage hashtagStorage, PostStorage postStorage) {
        this.hashtagStorage = hashtagStorage;
        this.postStorage = postStorage;
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
    @PostMapping("/hashtag/{postId}")
    public String addHashtag(String name, @PathVariable Long postId){
        Hashtag hashtagToAdd = new Hashtag(name, postStorage.retrievePostById(postId));
        hashtagStorage.save(hashtagToAdd);
        Post post = postStorage.retrievePostById(postId);
        post.addHashtag(hashtagToAdd);
        postStorage.save(post);

        return "redirect:/posts/" + postId;
    }


//    public String addHashtag(String name, Long id) {
//        return "redirect:/posts/" + id;
//    }
}
