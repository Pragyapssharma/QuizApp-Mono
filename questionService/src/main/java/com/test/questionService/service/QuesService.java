package com.test.questionService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.questionService.Question;
import com.test.questionService.QuestionWrapper;
import com.test.questionService.Response;
import com.test.questionService.dao.QuesDao;

@Service
public class QuesService 
{
	
	@Autowired
	QuesDao dao;
	
	public ResponseEntity<List<Question>> getAllQuestion() 
	{
		try {
			return new ResponseEntity<> (dao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ResponseEntity<> (new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuesByCategory(String category) 
	{
//		return dao.findByCategory(category);
		try {
			return new ResponseEntity<> (dao.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ResponseEntity<> (new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> addQues(Question q) 
	{
		dao.save(q);
//		return "success";
		return new ResponseEntity<> ("success", HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteQues(Integer qid) 
	{
		try {
			dao.deleteById(qid);
			return new ResponseEntity<> ("Deleted succesfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ResponseEntity<> ("error found", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> updateQues(Integer id, Question q) 
	{
		Question p = dao.findById(id).orElseThrow();
		try 
		{
			p.setCategory(q.getCategory());
			p.setCorrectAns(q.getCorrectAns());
			p.setDifficultyLevel(q.getDifficultyLevel());
			p.setOpt1(q.getOpt1());
			p.setOpt2(q.getOpt2());
			p.setOpt3(q.getOpt3());
			p.setOpt4(q.getOpt4());
			p.setQuestion(q.getQuestion());
				
			dao.save(p);
				
			return new ResponseEntity<> ("Updated succesfully", HttpStatus.OK);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return new ResponseEntity<> ("error found", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Integer>> quizQues(String category, Integer numQ) {
		
		List<Integer> ques = dao.findRandomQuesByCategory(category, numQ);
		
		return new ResponseEntity<> (ques, HttpStatus.OK);
	}
	
	public ResponseEntity<List<QuestionWrapper>> generateQues(List<Integer> id)
	{
		List<QuestionWrapper> wrapper = new ArrayList<>();
		List<Question> ques = new ArrayList<>();
		
		for(Integer i : id)
		{
			ques.add(dao.findById(i).get());
		}
		
		for(Question q : ques)
		{
			QuestionWrapper w = new QuestionWrapper(null, null, null, null, null, null);
			w.setQid(q.getQid());
			w.setQuestion(q.getQuestion());
			w.setOpt1(q.getOpt1());
			w.setOpt2(q.getOpt2());
			w.setOpt3(q.getOpt3());
			w.setOpt4(q.getOpt4());
			
			wrapper.add(w);
		}
		
		return new ResponseEntity<> (wrapper, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right = 0;
		
		for(Response r : responses)
		{
			Question ques = dao.findById(r.getId()).get();
			if(r.getResponse().equals(ques.getCorrectAns()))
				right++;
		}
		
		return new ResponseEntity<> (right, HttpStatus.OK);
	}
	
}
