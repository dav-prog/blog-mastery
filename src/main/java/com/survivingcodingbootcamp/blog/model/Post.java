package com.survivingcodingbootcamp.blog.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    private Topic topic;
    @Lob
    private String content;
    @ManyToMany(mappedBy = "posts")
    private Collection<Hashtag> hashtags;
    private String author;

    protected Post() {
    }

    public Post(String title, Topic topic, String content, String author) {
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public Collection<Hashtag> getHashtags() {
        return hashtags;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", topic=" + topic +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (topic != null ? !topic.equals(post.topic) : post.topic != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return author != null ? author.equals(post.author) : post.author == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    public void addHashtag(Hashtag hashtagToAdd) {
        hashtags.add(hashtagToAdd);
    }
}
