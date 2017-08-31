package app.model;

import java.util.ArrayList;

public class QuestionList {
	ArrayList<Question> list = new ArrayList<>();
	
	public void addQuestion(Question question) {
		this.list.add(question);
		}
	public Question getQuestion(int index) {
		return this.list.get(index);
	}
	public int getSize() {
		return list.size();
	}

}

