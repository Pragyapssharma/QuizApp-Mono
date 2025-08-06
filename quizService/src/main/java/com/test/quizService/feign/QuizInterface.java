package com.test.quizService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.quizService.QuestionWrapper;
import com.test.quizService.Response;


@FeignClient("questionService")
public interface QuizInterface {
	
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> quizQues(@RequestParam String category, @RequestParam Integer numQ);
	
	@PostMapping("question/generateQues")
	public ResponseEntity<List<QuestionWrapper>> generateQues(@RequestBody List<Integer> id);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
	
}
