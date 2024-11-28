package com.test.quizService;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Quiz 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer qid;
	private String title;
	
	@ElementCollection
	private List<Integer> quesID;
	
	
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
	public List<Integer> getQuesID() {
		return quesID;
	}
	public void setQuesID(List<Integer> quesID) {
		this.quesID = quesID;
	}
	
}
