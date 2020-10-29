package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HashtagControllerTest {

    private HashtagController underTest;
    private PostStorage postStorage;
    private HashtagStorage hashtagStorage;

    @BeforeEach
    void setUp() {
        hashtagStorage = mock(HashtagStorage.class);
        postStorage = mock(PostStorage.class);
        underTest = new HashtagController(hashtagStorage, postStorage);

    }

    @Test
    public void addNewHashtagShouldAddNewHashtag(){
        String redirectionInstructions = underTest.addHashtag("name", 1L);
        assertThat(redirectionInstructions).isEqualTo("redirect:/posts/1");
    }
    @Test
    public void addNewHashtagShouldAddNewHashtagToStorage(){
        Post mockPost = mock(Post.class);
        when(postStorage.retrievePostById(1L)).thenReturn(mockPost);

        underTest.addHashtag("name", 1L);

        verify(hashtagStorage).save(new Hashtag("name", mockPost));
    }



}
