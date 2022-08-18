package com.project.questapp.requests;

import lombok.Data;

@Data
public class CommentUpdateRequest {

	String text;

	public CommentUpdateRequest()
	{
		
	};
	
	public CommentUpdateRequest(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
