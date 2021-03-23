package com.empresa.post.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.empresa.post.model.TextPost;
import com.empresa.post.repository.TextPostRepository;

@ExtendWith(value=MockitoExtension.class)
public class TextPostServiceTests {

	@Mock
	private TextPostRepository textPostRepository;
	
	@InjectMocks
	private TextPostService textPostService;
	
	@Test
	public void test_save() {
		
		//given
		TextPost textPost = TextPost.builder().build();
		when(textPostRepository.save(textPost))
		.thenReturn(TextPost.builder().id(1).text("Text 1").build());
	
		//when
		TextPost actual = textPostService.create(textPost);
		
		//then
		assertEquals(1, actual.getId());
	}
}

