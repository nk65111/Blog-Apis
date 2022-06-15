package com.blog.api.blogapi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;


    private String postTitle;
    
    private String postContent;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments=new ArrayList<>();

}
