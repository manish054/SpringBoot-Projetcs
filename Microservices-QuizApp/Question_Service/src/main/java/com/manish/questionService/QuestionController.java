package com.manish.questionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.manish.questionService.modal.Question;
import com.manish.questionService.modal.QuestionWrapper;
import com.manish.questionService.modal.UserResponse;
import com.manish.questionService.service.QuestnService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private QuestnService qService;
	
	@GetMapping("all")
	public ResponseEntity<List<Question>> allQuestions(){
		return qService.allQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return qService.getQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return qService.addQuestion(question);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
		return qService.deleteQuestion(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
		return qService.updateQuestion(question);
	}
	
	//Create_Quiz
	//get/{Quiz_Id}
	//calculate Result -> as all these three depends on Question so implementing here to make it independent i.e., Microservices
	
	//Create Quiz
	@GetMapping("/createquiz")
	public ResponseEntity<List<Integer>> createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam int num){
		return qService.generateQuestion(category, num);
	}
	
	//get/{Quiz_id}
	@PostMapping("/getQuiz")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromQuiz(@RequestBody List<Integer> quesIds){
		return qService.getQuestionFromId(quesIds);
	}
	
	//calculate result
	@PostMapping("/getScore")
	public ResponseEntity<Integer> calculateScore(@RequestBody List<UserResponse> uResponse){
		return qService.calculateScore(uResponse);
	}
}
