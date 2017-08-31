package app.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import app.Database.DbConnector;

public class GetQuestions {
	
	QuestionList allQuestions;
	public QuestionList selectedQuestionList;
	DbConnector db;
	
	String[] catName = new String[5];
	
	public GetQuestions(boolean[] category, int amounrOfQuestionToSelect) throws ClassNotFoundException, SQLException {
		
		catName[0] = "db";
		catName[1] = "fe";
		catName[2] = "java";
		catName[3] = "python";
		catName[4] = "spring";
		
		db = new DbConnector();
		allQuestions = new QuestionList();
		selectedQuestionList = new QuestionList();
		
		int categoryIndex = 0;
    	for(boolean cat : category) {
    		if(cat)
    			getQuestionFromDatabase(catName[categoryIndex]);
    		categoryIndex++;
    	}	
    	
    	int allQuestionsListSize = allQuestions.getSize();
    	selectedQuestionList = new QuestionList();
    	
    	Random random = new Random();
    	for(int i = 0; i < amounrOfQuestionToSelect; i++) {
    		selectedQuestionList.addQuestion(allQuestions.getQuestion(random.nextInt(allQuestionsListSize)));
    	}
	}
	
	void getQuestionFromDatabase(String category) throws ClassNotFoundException, SQLException {
    	String sqlQuery = "select * FROM questions where category = '"+category+"';"; 
		Connection conn1 = db.connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs = stat.executeQuery(sqlQuery);
    	while(rs.next()) {
    		this.allQuestions.addQuestion(new Question(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
    	}
    }
}
