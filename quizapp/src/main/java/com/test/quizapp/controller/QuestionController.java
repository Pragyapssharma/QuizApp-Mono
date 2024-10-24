package com.test.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.quizapp.Question;
import com.test.quizapp.service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController 
{
	
	@Autowired
	QuestionService ser;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() 
	{
		return ser.getAllQuestion();
	}
	
	@GetMapping("category/{cat}")
	public ResponseEntity<List<Question>> getQuesByCategory(@PathVariable("cat") String category) 
	{
		return ser.getQuesByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQues(@RequestBody Question q) 
	{
		return ser.addQues(q);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteQues(@PathVariable Integer id) 
	{
		return ser.deleteQues(id);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<String> updateQues(@PathVariable Integer id, @RequestBody Question q) 
	{
		return ser.updateQues(id, q);
	}
}
