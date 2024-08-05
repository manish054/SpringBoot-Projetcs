package com.manish.quizService.modal;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Quiz {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ElementCollection
	private List<Integer> quesIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getQuesIds() {
		return quesIds;
	}

	public void setQuesIds(List<Integer> quesIds) {
		this.quesIds = quesIds;
	}

	public Quiz(Integer id, String title, List<Integer> quesIds) {
		super();
		this.id = id;
		this.title = title;
		this.quesIds = quesIds;
	}

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
