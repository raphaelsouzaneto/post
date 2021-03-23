package com.empresa.post.config;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.empresa.post.model.TextPost;
import com.empresa.post.repository.TextPostRepository;

@Component
public class DataLoader {

    @Autowired
    private TextPostRepository textPostRepository;

    //method invoked during the startup
    @PostConstruct
    public void loadData() {
    	textPostRepository.save(TextPost.builder().text("Texto 1").build());
    	textPostRepository.save(TextPost.builder().text("Texto 2").build());
    }

    //method invoked during the shutdown
    @PreDestroy
    public void removeData() {
    	textPostRepository.deleteAll();
    }
}