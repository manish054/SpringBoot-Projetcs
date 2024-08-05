package com.manish.questionService.service;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manish.questionService.modal.Question;
import com.manish.questionService.modal.QuestionWrapper;
import com.manish.questionService.modal.UserResponse;
import com.manish.questionService.repository.QuestnRepo;

@Service
public class QuestnService {
	
	@Autowired
	private QuestnRepo qRepo;
	
	public ResponseEntity<List<Question>> allQuestions() {
		// TODO Auto-generated method stub
		List<Question> list = qRepo.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(qRepo.findAllByCategory(category), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Question> addQuestion(Question question) {
		try {
			qRepo.save(question);
			return new ResponseEntity<>(question, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> deleteQuestion(int id) {
		// TODO Auto-generated method stub
		Optional<Question> ques = qRepo.findById(id);
		if(ques.isPresent()) {
			try {
				qRepo.deleteById(id);
				return new ResponseEntity<>("Deleted", HttpStatus.CREATED);
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> updateQuestion(Question question) {
		// TODO Auto-generated method stub
		Optional<Question> ques = qRepo.findById(question.getId());
		if(ques.isPresent()) {
			try {
				qRepo.save(question);
				return new ResponseEntity<>("Updated", HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Integer>> generateQuestion(String category, int num) {
		// TODO Auto-generated method stub
		List<Integer> qus = qRepo.findRandomQuestionsByCategory(category, num);
//		System.out.println("qus---"+qus.toString());
		return new ResponseEntity<>(qus, HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> quesIds) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> questions = new ArrayList<>();
		for(Integer id:quesIds) {
			Question q = qRepo.findById(id).get();
			QuestionWrapper qWrapper = new QuestionWrapper();
			qWrapper.setId(q.getId());
			qWrapper.setQuestion(q.getQuestion());
			qWrapper.setOption1(q.getOption1());
			qWrapper.setOption2(q.getOption2());
			qWrapper.setOption3(q.getOption3());
			qWrapper.setOption4(q.getOption4());
			questions.add(qWrapper);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateScore(List<UserResponse> uResponse) {
		// TODO Auto-generated method stub
		int score = 0;
		for(UserResponse res:uResponse) {
			String correctAns = qRepo.getAnsById(res.getId());
			if(res.getResponse().equals(correctAns)) {
				score++;
			}
		}
		return new ResponseEntity<Integer>(score, HttpStatus.OK);
	}

}
