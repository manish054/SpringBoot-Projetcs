package com.manish.quizService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.manish.quizService.modal.QuestionWrapper;
import com.manish.quizService.modal.UserResponse;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	
	//Create Quiz
		@GetMapping("question/createquiz")
		public ResponseEntity<List<Integer>> createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam int num);
		
		//get/{Quiz_id}
		@PostMapping("question/getQuiz")
		public ResponseEntity<List<QuestionWrapper>> getQuestionFromQuiz(@RequestBody List<Integer> quesIds);
		
		//calculate result
		@PostMapping("question/getScore")
		public ResponseEntity<Integer> calculateScore(@RequestBody List<UserResponse> uResponse);
}
