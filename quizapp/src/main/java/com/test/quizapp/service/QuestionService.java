package com.test.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.quizapp.Question;
import com.test.quizapp.dao.QuestionDao;

@Service
public class QuestionService 
{
	
	@Autowired
	QuestionDao dao;
	
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
}
