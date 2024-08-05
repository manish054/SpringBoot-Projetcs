package com.manish.questionService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manish.questionService.modal.Question;

@Repository
public interface QuestnRepo extends JpaRepository<Question, Integer>{
	List<Question> findAllByCategory(String category);
	
	@Query(value="SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :num", nativeQuery = true)
	List<Integer> findRandomQuestionsByCategory(String category, int num);
	
	@Query(value="SELECT q.answer FROM question q WHERE q.id= :id", nativeQuery = true)
	String getAnsById(Integer id);
}
