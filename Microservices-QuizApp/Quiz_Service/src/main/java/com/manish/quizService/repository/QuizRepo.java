package com.manish.quizService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manish.quizService.modal.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
