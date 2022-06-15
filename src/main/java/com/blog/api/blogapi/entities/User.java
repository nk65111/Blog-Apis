package com.blog.api.blogapi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "username",length = 200,nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
    
    @Column(nullable =  false)
    private String password;
     
    @Column(length = 500)
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

}
