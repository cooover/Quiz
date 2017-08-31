package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DbConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	
	static boolean cat1;
	static boolean cat2;
	static boolean cat3;
	static boolean cat4;
	static boolean cat5;

	@FXML
    private AnchorPane ap_cat;
	@FXML
    private ComboBox<Integer> combo;
	@FXML
    private CheckBox cb_db;
    @FXML
    private CheckBox cb_python;
    @FXML
    private CheckBox cb_front;
    @FXML
    private CheckBox cb_java;
    @FXML
    private CheckBox cb_spring;
    @FXML
    private Button btn_start;
    @FXML
    private AnchorPane ap_log;
    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField pf_pass;
    @FXML
    private Button btn_log;
   
    public DbConnector db;
    static String logowanie;
    static int amountOfQuestions;
    static int id_user;
    
    @FXML
    void loginAction(MouseEvent event) throws ClassNotFoundException, SQLException  {
    	Connection conn1 = db.connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs = stat.executeQuery("select * from users where login ='"+tf_login.getText()+"'and pass='"+pf_pass.getText()+"';");
    	if(rs.next()) {
    		ap_cat.setVisible(true);
    		ap_log.setVisible(false);
    		logowanie = rs.getString("login");   
    		id_user = rs.getInt("id_user");
    	}else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Poda³eœ b³êdny login lub has³o");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}
    }

    @FXML
    void startAction(MouseEvent event) throws IOException {
    	
    	if(!cb_db.isSelected()&&!cb_front.isSelected()&&!cb_java.isSelected()&&!cb_spring.isSelected()&&!cb_python.isSelected()) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Brak kategorii");
    		alert.setHeaderText("UWAGA!");
    		alert.setContentText("Musisz zaznaczyæ kategoriê");
    		alert.showAndWait();
    		return;
		}

    	
    	cat1 = cb_db.isSelected();
    	cat2 = cb_front.isSelected();
    	cat3 = cb_java.isSelected();
    	cat4 = cb_python.isSelected();
    	cat5 = cb_spring.isSelected();
    	

    	amountOfQuestions = combo.getSelectionModel().getSelectedItem();

		Stage stageTable = new Stage();
		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/app/view/QuizeView.fxml"));
		Scene sceneTable = new Scene(parent);
		stageTable.setScene(sceneTable);
		stageTable.setTitle("Question");
		stageTable.getIcons().add(new Image("http://www.myandroidsolutions.com/wp-content/uploads/2016/12/Quiz-Time.png"));
		stageTable.show();
	    
    }
    public void initialize() {
    	db = new DbConnector();
    	combo.getItems().add(5);
    	combo.getItems().add(10);
    	combo.getItems().add(15);
    	combo.getItems().add(20);
    	combo.getSelectionModel().select(0);
    }
}
