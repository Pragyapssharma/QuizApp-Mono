package com.test.quizService;

import lombok.Data;

@Data
public class QuizDTO {
	
	private String category;
	private Integer numQues;
	private String title;
	
	public String getCategory() 
	{
		return category;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}
	public Integer getNumQues() 
	{
		return numQues;
	}
	public void setNumQues(Integer numQues) 
	{
		this.numQues = numQues;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
}
