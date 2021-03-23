package com.empresa.post.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.empresa.post.model.TextPost;
import com.empresa.post.service.TextPostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TextPostController.class)

public class TextPostControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TextPostService textPostService;
	
	@Autowired
	private ObjectMapper objectMapper;	
	
	@Test
	public void test_getAll_thenReturns200() throws Exception{
		//given
		List<TextPost> list = new ArrayList<>();
		list.add(TextPost.builder().id(1).text("Text 1").build());
		list.add(TextPost.builder().id(2).text("Text 2").build());
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/textpost");
		
		//and
		when(textPostService.findAllTextPosts())
		.thenReturn(list);
		
		//and
		mockMvc.perform(request).andDo(print())
		
		//Then
		.andExpect(status().isOk());
	}
	
	  @Test
	  void test_postObject_thenReturns200() throws Exception {
		
		//given
		TextPost textPost = TextPost.builder().text("Text Post").build();

		RequestBuilder request = MockMvcRequestBuilders
				.post("/textpost")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(textPost));
		
		
		//and
		when(textPostService.create(any()))
		.thenReturn(TextPost.builder().id(1).text("Text Post").build());
		
		//and
		mockMvc.perform(request).andDo(print())
		
		//Then
		.andExpect(status().isCreated());
		
	  }
	  
	  @Test
	  void test_putObject_upvote_thenReturns200() throws Exception {
		
		//given
		RequestBuilder request = MockMvcRequestBuilders
				.put("/textpost/1/upvote")
				.contentType("application/json");
		
		//and
		when(textPostService.upvote(any()))
		.thenReturn(TextPost.builder().id(1).text("Text Post upvote").build());
		
		//and
		mockMvc.perform(request).andDo(print())
		
		//Then
		.andExpect(status().isOk());
		
	  }	  
}

