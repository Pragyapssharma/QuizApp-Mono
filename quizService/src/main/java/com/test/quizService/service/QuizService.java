package com.test.quizService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.quizService.QuestionWrapper;
import com.test.quizService.Quiz;
import com.test.quizService.Response;
import com.test.quizService.dao.QuizDao;
import com.test.quizService.feign.QuizInterface;



@Service
public class QuizService 
{
	
	@Autowired
	QuizDao qzdao;
	
	@Autowired
	QuizInterface qi;

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) 
	{
		List<Integer> q = qi.quizQues(category, numQ).getBody();
		Quiz qz = new Quiz();
		qz.setTitle(title);
		qz.setQuesID(q);
		
		qzdao.save(qz);
		
		return new ResponseEntity<> ("Successfully created", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) 
	{
		Quiz qz = qzdao.findById(id).get();
		List<Integer> qid = qz.getQuesID();
		ResponseEntity<List<QuestionWrapper>> ques = qi.generateQues(qid);
		
		return ques;
	}
	
	public ResponseEntity<Integer> submitQues(Integer id, List<Response> responses) 
	{
		ResponseEntity<Integer> score = qi.getScore(responses);
		
		return score;
	}
	
}
