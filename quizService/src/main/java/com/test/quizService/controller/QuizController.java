package com.test.quizService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.quizService.QuestionWrapper;
import com.test.quizService.QuizDTO;
import com.test.quizService.Response;
import com.test.quizService.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController 
{
	
	@Autowired
	QuizService ser;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDTO dto) 
	{
		return ser.createQuiz(dto.getCategory(), dto.getNumQues(), dto.getTitle());
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
