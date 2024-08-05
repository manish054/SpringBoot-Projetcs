package com.manish.quizService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manish.quizService.feign.QuizInterface;
import com.manish.quizService.modal.QuestionWrapper;
import com.manish.quizService.modal.Quiz;
import com.manish.quizService.modal.UserResponse;
import com.manish.quizService.repository.QuizRepo;


@Service
public class QuizService {

	
	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuizInterface quizInterface;
	
	public ResponseEntity<String> createQuiz(String category, int num, String title){
		
		//calling the createquiz url from question controller - RestTemplate http://localhost:8080/question/createquiz
		List<Integer> qIds = quizInterface.createQuiz(category, title, num).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuesIds(qIds);
		quizRepo.save(quiz);
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
		Quiz q = quizRepo.findById(id).get();
		List<Integer> qIds = q.getQuesIds();
		return new ResponseEntity<>(quizInterface.getQuestionFromQuiz(qIds).getBody(), HttpStatus.OK);
	}

	public ResponseEntity<Integer> getResult(List<UserResponse> uResponse) {
//		int correctAns = 0;
//		int index = 0;
//		Optional<Quiz> quiz = quizRepo.findById(id);
//		List<Question> ques = quiz.get().getQuestions();
//		if(uResponse.size() == 0) {
//			return new ResponseEntity<>(correctAns, HttpStatus.BAD_REQUEST);
//		}
//		
//		for(UserResponse res:uResponse) {
////			Optional<Question> quesFromDB = questionRepo.findById(res.getId());
//			if(res.getResponse().equals(ques.get(index).getAnswer())) {
//				correctAns++;
//			}
//			index++;
//		}
		
		return new ResponseEntity<>(quizInterface.calculateScore(uResponse).getBody(), HttpStatus.OK);

	}

}
