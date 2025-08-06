package com.test.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.quizapp.QuestionWrapper;
import com.test.quizapp.Response;
import com.test.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController 
{
	
	@Autowired
	QuizService ser;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title) 
	{
		return ser.createQuiz(category, numQ, title);
	}
	
	@GetMapping("getQuiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id) 
	{
		return ser.getQuiz(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQues(@PathVariable Integer id, @RequestBody List<Response> responses) 
	{
		return ser.submitQues(id, responses);
	}
	
}
