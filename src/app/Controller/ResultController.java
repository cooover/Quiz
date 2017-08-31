package app.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DbConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ResultController {

    @FXML
    private Label lbl_login;
    @FXML
    private Label lbl_result_failed;

    @FXML
    private Label lbl_result_failed_score;

    @FXML
    private Label lbl_result1_passed;

    @FXML
    private Label lbl_result1_passed_score;
    @FXML
    private Label lbl_all_results;
    @FXML
    private ImageView iv_passed;
    @FXML
    private ImageView iv_failed;
    
    DbConnector db;
    static double suma;
    static int ilosc_pyt;
    

    @FXML
    void closeAction(MouseEvent event) {
    	System.exit(0);
    }
    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
    	db = new DbConnector();
    	if(QuizController.result>0.5) {
    		iv_passed.setVisible(true);
    		lbl_result1_passed.setVisible(true);
    		lbl_result1_passed_score.setVisible(true);
    		
    	}else {
    		iv_failed.setVisible(true);
    		lbl_result_failed.setVisible(true);
    		lbl_result_failed_score.setVisible(true);
    	}
    	Connection conn1 = db.connection();
    	Statement stat = conn1.createStatement();
    	ResultSet sum = stat.executeQuery("select sum(result) as licznik from results where id_user="+LoginController.id_user+";");
    	sum.next();
    	suma = sum.getDouble("licznik");
    	
    	ResultSet amount = stat.executeQuery("select count(*) as mianownik from results where id_user="+LoginController.id_user+";");
    	amount.next();
    	ilosc_pyt = amount.getInt("mianownik");
    	
    	lbl_login.setText(LoginController.logowanie);
    	lbl_result1_passed_score.setText(String.valueOf(QuizController.result*100)+"%");
    	lbl_result_failed_score.setText(String.valueOf(QuizController.result*100)+"%");
    	lbl_all_results.setText(String.valueOf(Math.round(suma/ilosc_pyt*10000)/100d)+"%");
    }
}
