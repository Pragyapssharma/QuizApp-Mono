package com.test.quizapp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Entity
public class Quiz 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer qid;
	private String title;
	
	@ManyToMany
	private List<Question> ques;
	
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Question> getQues() {
		return ques;
	}
	public void setQues(List<Question> ques) {
		this.ques = ques;
	}
	
	
}
