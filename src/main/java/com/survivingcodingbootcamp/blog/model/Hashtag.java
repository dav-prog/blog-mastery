package com.survivingcodingbootcamp.blog.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Hashtag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private Collection<Post> posts;

    protected Hashtag (){}

    public Hashtag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hashtag hashtag = (Hashtag) o;

        if (!id.equals(hashtag.id)) return false;
        return name.equals(hashtag.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
