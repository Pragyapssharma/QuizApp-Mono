package com.test.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.quizapp.Question;
import com.test.quizapp.QuestionWrapper;
import com.test.quizapp.Quiz;
import com.test.quizapp.Response;
import com.test.quizapp.dao.QuestionDao;
import com.test.quizapp.dao.QuizDao;

@Service
public class QuizService 
{
	
	@Autowired
	QuizDao qzdao;
	
	@Autowired
	QuestionDao qsdao;

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) 
	{
		List<Question> ques = qsdao.findRandomQuesByCategory(category, numQ);
		
		Quiz q = new Quiz();
		q.setTitle(title);
		q.setQues(ques);
		
		qzdao.save(q);
		
		return new ResponseEntity<> ("Successfully created", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) 
	{
		Optional<Quiz> qz = qzdao.findById(id);
		List<Question> qs = qz.get().getQues();
		List<QuestionWrapper> ques = new ArrayList<>();
		
		for(Question q : qs) {
			QuestionWrapper qw = new QuestionWrapper(q.getQid(), q.getQuestion(), q.getOpt1(), q.getOpt2(), q.getOpt3(), q.getOpt4());
			ques.add(qw);
		}
		
		return new ResponseEntity<> (ques, HttpStatus.OK);
	}
	
	public ResponseEntity<Integer> submitQues(Integer id, List<Response> responses) 
	{
		Quiz qz = qzdao.findById(id).get();
		
		List<Question> ques = qz.getQues();
		
		int right = 0;
		int i=0;
		
		for(Response r : responses)
		{
			if(r.getResponse().equals(ques.get(i).getCorrectAns()))
				right++;
			
			i++;
		}
		
		return new ResponseEntity<> (right, HttpStatus.OK);
	}
	
}
