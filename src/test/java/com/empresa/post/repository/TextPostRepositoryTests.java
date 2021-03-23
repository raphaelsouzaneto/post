package com.empresa.post.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.empresa.post.model.TextPost;

@DataJpaTest
public class TextPostRepositoryTests {

	@Autowired
	private TestEntityManager tem;
	 
	@Autowired
	private TextPostRepository textPostRepository;

	
	@Test
	@DisplayName("Deve encontrar textpost por id")
	public void test_findById() {
		//given
		TextPost t1 = TextPost.builder().text("Text Test").build();
		t1 = tem.persistAndFlush(t1);
		
		//when
		TextPost find = textPostRepository.findById(t1.getId()).get();
		
		//then
		assertEquals(find.getText(), "Text Test");
	}
}

