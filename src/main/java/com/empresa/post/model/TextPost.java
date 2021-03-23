package com.empresa.post.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextPost {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private int upvote;
	
}
