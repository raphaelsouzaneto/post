package com.empresa.post.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.post.model.TextPost;
import com.empresa.post.repository.TextPostRepository;

@Service
public class TextPostService {
	
	@Autowired
	private TextPostRepository textPostRepository;
	
	public List<TextPost> findAllTextPosts(){
		return textPostRepository.findAll();
	}
	
	public TextPost create(TextPost textPost) {
		return textPostRepository.save(textPost);
	}
	
	public TextPost upvote(Integer id) {
		Optional<TextPost> find = textPostRepository.findById(id);
		TextPost entity = find.get();
		entity.setUpvote(entity.getUpvote() + 1);
		
		entity = textPostRepository.save(entity);
		return entity;
	}

}
