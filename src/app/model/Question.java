package app.model;

public class Question {
	public String question;
	public String ans_a;
	public String ans_b;
	public String ans_c;
	public String ans_d;
	public String answer;
	public String katrgory;
	
	
	public Question(String kategory, String question, String ans_a, String ans_b, String ans_c, String ans_d, String answer) {
		super();
		this.katrgory = kategory;
		this.question = question;
		this.ans_a = ans_a;
		this.ans_b = ans_b;
		this.ans_c = ans_c;
		this.ans_d = ans_d;
		this.answer = answer;
	}
	
	

}
