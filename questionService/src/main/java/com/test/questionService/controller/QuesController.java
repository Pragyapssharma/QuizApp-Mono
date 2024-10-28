package com.test.questionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.questionService.Question;
import com.test.questionService.QuestionWrapper;
import com.test.questionService.Response;
import com.test.questionService.service.QuesService;


@RestController
@RequestMapping("question")
public class QuesController 
{
	
	@Autowired
	QuesService ser;
	
	@Autowired
	Environment e;
	
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
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> quizQues(@RequestParam String category, @RequestParam Integer numQ)
	{
		return ser.quizQues(category, numQ);
	}
	
	@PostMapping("generateQues")
	public ResponseEntity<List<QuestionWrapper>> generateQues(@RequestBody List<Integer> id)
	{
		System.out.println(e.getProperty("local.server.port"));
		return ser.generateQues(id);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return ser.getScore(responses);
	}
	
}
