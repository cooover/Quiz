package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import app.Database.DbConnector;
import app.model.GetQuestions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QuizController {
	
	boolean[] cat;	//tablica przechowyje zaznaczone przy logowaniu kategorie 
	
	int sumResult = 0; //liczba poprawnych odpowiedzi
	static double result = 0.; //wynik testu

	int numberOfQuestions = LoginController.amountOfQuestions; //liczba pytañ w teœcie
	int curentQuestion = 0; //numer aktualnego pytania(indeks)
	GetQuestions questions; // obiekt przechowuje listê pytañ

	DbConnector db; //konektor do BD

    @FXML
    private Label label_questNumber;
    @FXML
    private Label label_numberOfQuestions;
    @FXML
    private Label label_content;
    @FXML
    private RadioButton rb_ansA;
    @FXML
    private ToggleGroup answer;
    @FXML
    private RadioButton rb_ansB;
    @FXML
    private RadioButton rb_ansC;
    @FXML
    private RadioButton rb_ansD;
    @FXML
    private Button btn_next;
    
    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
    	
    	db = new DbConnector();
    	
    	cat = new boolean[5];	//pobranie zaznaczonych kategorii do tablicy
    	cat[0] = LoginController.cat1;
    	cat[1] = LoginController.cat2;
    	cat[2] = LoginController.cat3;
    	cat[3] = LoginController.cat4;
    	cat[4] = LoginController.cat5;
    	
    	// pobranie pytañ z DB
    	questions = new GetQuestions(cat, numberOfQuestions);
    	
    	// ustawienie iloœci pytañ
    	label_numberOfQuestions.setText(String.valueOf(numberOfQuestions));
    	showQuestion();
    }
    
    // Metoda wypisuje w aplikacji pytanie i odpowiedzi
    @FXML
    public void showQuestion() {
    	label_questNumber.setText(String.valueOf(curentQuestion+1));
    	label_content.setText(questions.selectedQuestionList.getQuestion(curentQuestion).question);
    	rb_ansA.setText(questions.selectedQuestionList.getQuestion(curentQuestion).ans_a);
    	rb_ansB.setText(questions.selectedQuestionList.getQuestion(curentQuestion).ans_b);
    	rb_ansC.setText(questions.selectedQuestionList.getQuestion(curentQuestion).ans_c);
    	rb_ansD.setText(questions.selectedQuestionList.getQuestion(curentQuestion).ans_d);
    	
    	rb_ansA.setSelected(false);
    	rb_ansB.setSelected(false);
    	rb_ansC.setSelected(false);
    	rb_ansD.setSelected(false);
    	
    	//zmiana napisu na przycisku NEXT jeœli ostatnie pytanie
    	if(curentQuestion == numberOfQuestions-1 )
    		btn_next.setText("Zakoñcz"); 	
    }
    
    //metoda obs³uguje akcê przycisku NEXT
    @FXML
    void nextQuestAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	
		if(!rb_ansA.isSelected()&&!rb_ansB.isSelected()&&!rb_ansC.isSelected()&&!rb_ansD.isSelected()) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Brak odpowiedzi");
    		alert.setHeaderText("Brak odpowiedzi!");
    		alert.setContentText("Musisz zaznaczyæ odpowiedz");
    		alert.showAndWait();
    		return;
		}
		
		//jeœli poprawna odpowiedz dodaj 1 punkt do wyniku
    	if(checkAnswer(curentQuestion))
    		sumResult += 1;

    	//jeœli nie ostatnie pytanie
    	if(curentQuestion < (numberOfQuestions-1)) {
    		curentQuestion ++; 
    		showQuestion();	
	    }
    	//jeœli ostatnie pytanie
    	else {
    		btn_next.setDisable(true);	//wy³¹cz przycisk
    		result = sumResult/(double)numberOfQuestions;	//oblicz wynik
    		
    		resultDbInbsert();	//wyœlij wynik do DB
    		
    		//info o wyniku
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Wynik testu");
    		if(result >= 0.5)	//test zdany
    			alert.setHeaderText("Gratulacje!");
    		else	//test NIE zdany 
    			alert.setHeaderText("Niestety!");
    		alert.setContentText("Twój wynik to: "+ (result*100)+"%");
    		alert.showAndWait();
    		
    		//Wyœwietl okno z wynikami
    		Stage stageTable = new Stage();
    		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/app/view/ResultView.fxml"));
    		Scene sceneTable = new Scene(parent);
    		stageTable.setScene(sceneTable);
    		stageTable.setTitle("Question");
    		stageTable.getIcons().add(new Image("http://www.myandroidsolutions.com/wp-content/uploads/2016/12/Quiz-Time.png"));
    		stageTable.show();
    	}
    }
    
    // metoda zwraca true jeœli na pytanie o przekazanym indeksie udzielono poprawnej odp
    boolean checkAnswer(int index) {
    	boolean re = false;
    	//pobranie poprawnej odpowiedzi na pytanie
    	String answer = questions.selectedQuestionList.getQuestion(index).answer;
    	answer = answer.toLowerCase();
    	
    	if(answer.equals("a"))
    		if(rb_ansA.isSelected())
    			re = true;
    	else if(answer.equals("b")) 
    		if(rb_ansB.isSelected())
    			re = true;
    	else if(answer.equals("c")) 
    		if(rb_ansC.isSelected()) 
    			re = true;
    	else if(answer.equals("d")) 
    		if(rb_ansD.isSelected()) 
    			re = true;
    	return re;
    }
    
    // Metoda wysy³a do bd wynik testu
    void resultDbInbsert() throws SQLException, ClassNotFoundException {    	
    	Connection conn = db.connection();
    	String sql = "INSERT INTO results (id_user, result) VALUES ("+LoginController.id_user+", "+result+");";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.executeUpdate();   	
    }
}
