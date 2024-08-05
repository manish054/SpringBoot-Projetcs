package com.manish.quizService;

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

import com.manish.quizService.modal.QuestionWrapper;
import com.manish.quizService.modal.QuizDto;
import com.manish.quizService.modal.UserResponse;
import com.manish.quizService.service.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNum(), quizDto.getTitle());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
		return quizService.getQuiz(id);
	}
	
	@PostMapping("/submit")
	public ResponseEntity<Integer> getResult(@RequestBody List<UserResponse> uResponse){
		return quizService.getResult(uResponse);
	}

}
