package com.empresa.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.post.model.TextPost;

@Repository
public interface TextPostRepository extends JpaRepository<TextPost, Integer> {

}

