package com.empresa.post.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.empresa.post.model.TextPost;
import com.empresa.post.repository.TextPostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TextPostIntegrationTests {

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  @Autowired
	  private TextPostRepository textPostRepository;

	  @Test
	  void createTextPostWorksThroughAllLayers() throws Exception {
		TextPost textPost = TextPost.builder().text("Text Integration").build();
		  
		RequestBuilder request = MockMvcRequestBuilders
				.post("/textpost")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(textPost));		
		
		MvcResult mvcResult = mockMvc.perform(request)
	            .andExpect(status().isCreated())
	            .andReturn();
	    
	    TextPost actualResponseBody = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TextPost.class);
	    TextPost expected = textPostRepository.findById(actualResponseBody.getId()).get();
	    
	    assertEquals(actualResponseBody.getText(), expected.getText());
	  }
	  
	  @Test
	  void findAllTextPostWorksThroughAllLayers() throws Exception {
		//given 
		RequestBuilder request = MockMvcRequestBuilders
				.get("/textpost")
				.contentType("application/json");
		
		//and
		mockMvc.perform(request).andDo(print())
		
		//Then
		.andExpect(status().isOk());
	  }	  
	  
	  @Test
	  void updateUpvoteWorksThroughAllLayers() throws Exception {
		  
		  	//create
			TextPost textPost = TextPost.builder().text("Text Integration").build();
			  
			RequestBuilder requestCreate = MockMvcRequestBuilders
					.post("/textpost")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(textPost));		
			
			MvcResult mvcResult = mockMvc.perform(requestCreate)
		            .andExpect(status().isCreated())
		            .andReturn();
		    
		    TextPost actualResponseBody = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TextPost.class);		  
		  
		  
		    // upvote 
			RequestBuilder requestPut = MockMvcRequestBuilders
					.put("/textpost/"+actualResponseBody.getId()+"/upvote")
					.contentType("application/json");
		
			mockMvc.perform(requestPut).andDo(print())
			.andExpect(status().isOk());	    
		    
		    
			//find
			
			TextPost find = textPostRepository.findById(actualResponseBody.getId()).get();
		  
			//expected
			assertNotEquals(actualResponseBody.getUpvote(), find.getUpvote());
			
	  }	 	  
	  
}

