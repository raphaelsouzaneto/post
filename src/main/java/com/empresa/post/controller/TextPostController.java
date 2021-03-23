package com.empresa.post.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.post.model.TextPost;
import com.empresa.post.service.TextPostService;

@RestController
@RequestMapping("/textpost")
public class TextPostController {
	
	private final TextPostService textPostService;
	
	public TextPostController(TextPostService textPostService) {
		this.textPostService = textPostService;
	}
	
	@GetMapping()
	public ResponseEntity<List<TextPost>> getAllTextPosts() {
		List<TextPost> textPosts = textPostService.findAllTextPosts();
		return new ResponseEntity<>(textPosts, HttpStatus.OK);
	}
	
    @PostMapping()
    public ResponseEntity<TextPost> addTextPost(@RequestBody TextPost textPost) {
    	TextPost newTextPost = textPostService.create(textPost);
        return new ResponseEntity<>(newTextPost, HttpStatus.CREATED);
    }	
	
    @PutMapping("/{id}/upvote")
    public ResponseEntity<?> updateUpVote(@PathVariable("id") Integer id) {
    	textPostService.upvote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
