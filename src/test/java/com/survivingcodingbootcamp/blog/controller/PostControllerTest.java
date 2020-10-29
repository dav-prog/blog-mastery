package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.TopicStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerTest {

    private PostController underTest;
    private Model model;
    private Post testPost;
    private TopicStorage topicStorage;
    private PostStorage postStorage;
    private Topic mockTopic;

    @BeforeEach
    void setUp() {
        postStorage = mock(PostStorage.class);
        topicStorage = mock(TopicStorage.class);
        underTest = new PostController(postStorage, topicStorage);
        model = mock(Model.class);
        testPost = mock(Post.class);
        when(postStorage.retrievePostById(1L)).thenReturn(testPost);


        mockTopic = mock(Topic.class);
    }

    @Test
    public void displaySinglePostShouldReturnPostTemplateName(){
        String templateName = underTest.displaySinglePost(1L, model);
        assertThat(templateName).isEqualTo("single-post-template");
    }

    @Test
    public void displaySinglePostShouldAddPostToModel(){
        underTest.displaySinglePost(1L, model);
        verify(model).addAttribute("post", testPost);
    }
    @Test
    public void displaySinglePostIsMappedForTheSinglePostEndpoint() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void addNewPostShouldRedirectToTopicPage(){
        String redirectionInstructions = underTest.addNewPost("title", 1L, "content", "author");
        assertThat(redirectionInstructions).isEqualTo("redirect:/topics/1");
    }
    @Test
    public void addNewPostShouldAddNewPostToStorage(){
        when(topicStorage.retrieveSingleTopic(2L)).thenReturn(mockTopic);


        underTest.addNewPost("title", 2L, "content", "author");

        verify(postStorage).save(new Post("title", mockTopic, "content", "author"));
    }
    @Test
    public void addNewPostIsWiredCorrectly() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        when(topicStorage.retrieveSingleTopic(3L)).thenReturn(mockTopic);
        mockMvc.perform(post("/posts")
                                .param("title","Test title")
                                .param("topicId", "3")
                                .param("content","Test Content")
                                .param("author","Test Author"))
                    .andExpect(status().is3xxRedirection());
        verify(postStorage).save(new Post("Test title", mockTopic, "Test Content", "Test Author"));

    }


}
