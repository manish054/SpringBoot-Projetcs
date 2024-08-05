package com.manish.quizService.modal;

public class QuizDto {
	private String category;
	private String title;
	private Integer num;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public QuizDto(String category, String title, Integer num) {
		super();
		this.category = category;
		this.title = title;
		this.num = num;
	}
	public QuizDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
