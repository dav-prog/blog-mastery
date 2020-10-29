package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.TopicStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/posts")
public class PostController {
    private PostStorage postStorage;
    private TopicStorage topicStorage;

    public PostController(PostStorage postStorage, TopicStorage topicStorage) {
        this.postStorage = postStorage;
        this.topicStorage = topicStorage;
    }

@GetMapping("/{id}")
    public String displaySinglePost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postStorage.retrievePostById(id));
        return "single-post-template";
    }

    @PostMapping("")
    public String addNewPost(String title, long topicId, String content, String author) {
        Topic postTopic = topicStorage.retrieveSingleTopic(topicId);
        postStorage.save(new Post(title, postTopic, content, author));

        return "redirect:/topics/" + topicId;
    }
}
