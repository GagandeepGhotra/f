package fray.GUIs.ControllerClasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import fray.JDBC.JavaDatabaseConnector;
//import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegistrationClass {
	
	@FXML
	private AnchorPane pane;
	
	//private Pane Content_Area;

	@FXML
	private JFXTextField fnTP, mnTP, lnTP, uTF, ppTF, pnTF, aTF, coTF, sopTF, cTF, zcTF; //First name, Middle name, Last name, User name, Password, Phone Number, Address, Country, State or Province, City, Zip Code
	
	@FXML
	private JFXButton continueToNextStepBtn;
	
	public static Stage stage = null;
	
	private JavaDatabaseConnector connection = getConnection();
	private Connection con;
	
	// Left out since it does not work properly!!
//	@FXML
//	private void back_to_menu(ActionEvent event) { 		
//		try {
////			FXMLLoader root = new FXMLLoader(getClass().getResource("/GraphicalUserInterfaceClasses/FXML/LoginClass.fxml"));
////			Parent root1 = (Parent) root.load();
////		
////			getContent_Area().getChildren().removeAll();
////			getContent_Area().getChildren().setAll(root1);
//			
//			Parent root = FXMLLoader.load(getClass().getResource("/GraphicalUserInterfaceClasses/FXML/LoginClass.fxml"));
//			getStage().getScene().setRoot(root);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
    public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	@FXML
    private void mousePressed(ActionEvent event) throws IOException {
    	boolean error = false;
    	String errors = "";
    	
    	if ((fnTP.getText().equals(null) || fnTP.getText().trim().isEmpty())) {
    		 error = true;
    		 errors += " First Name,";
    	}
    	
    	if ((mnTP.getText().equals(null) || mnTP.getText().trim().isEmpty())) {
    		error = true;
    		errors += " Middle Name,";
    	}
    	
    	if ((lnTP.getText().equals(null) || lnTP.getText().trim().isEmpty())) {
    		error = true;
    		errors += " Last Name,";
    	}
    	
    	if ((uTF.getText().equals(null) || uTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " User Name,";
    	}
    
    	if ((ppTF.getText().equals(null) || ppTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " Password,";
    	}
    	
    	if ((pnTF.getText().equals(null) || pnTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " Phone Number,";
    	}
    	
    	if ((aTF.getText().equals(null) || aTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " Address,";
    	}
    	
    	if ((coTF.getText().equals(null) || coTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " Country Name,";
    	}
    	
    	if ((sopTF.getText().equals(null) || sopTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " State or Province Name,";
    	}
    	
    	if ((cTF.getText().equals(null) || cTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " City Name,";
    	}
    	
    	if ((zcTF.getText().equals(null) || zcTF.getText().trim().isEmpty())) {
    		error = true;
    		errors += " Zip Code.";
    	}
    	
    	else {
    		JOptionPane.showMessageDialog(null, "Sign up successful!");
    		FXMLLoader root = new FXMLLoader(getClass().getResource("../FXML/MainPage.fxml"));
    		Parent root1 = (Parent) root.load();
    		registerCredintials();
    		
    		//MainPageController sec = root.getController();
    		//sec.passConnectionAndUsername(uTF.getText(), getConnection());
    		
    		Stage w = (Stage) ((Node)event.getTarget()).getScene().getWindow();
			w.setScene(new Scene(root1));
			w.show();
    	}
    	
    	if (error == true) {
    		Alert fail = new Alert(AlertType.ERROR);
	        fail.setHeaderText("Failure");
	        fail.setContentText("Empty Fields: " + errors);
	        fail.showAndWait();
    	}

    }
    
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		RegistrationClass.stage = stage;
	}

	private void registerCredintials() throws IOException {
			try {
				
				Statement stmt = con.createStatement();
				String sql = "INSERT INTO " + getConnection().getDatabaseName() + ".AccountInformation " 
				+ "(FirstName, MiddleName, LastName, Name, AccountPassword, PhoneNumber, Address, Country, StateorProvince, City, ZipCode) VALUES "
				+ "('" + fnTP.getText() + "', '" + mnTP.getText() + "', '" + lnTP.getText() + "', '" + uTF.getText() + "', '"
				+ ppTF.getText() + "', '" + pnTF.getText() + "', '" + aTF.getText() + "', '" + coTF.getText() + "', '" + sopTF.getText() + "', '"
				+ cTF.getText() + "', '" + zcTF.getText() + "');"; 
				stmt.executeUpdate(sql);
				
				PreparedStatement ps;
				String sql1 = "CREATE TABLE " + uTF.getText() + "_PaymentMethodsTable LIKE User_PaymentMethodsTable;";
				ps = con.prepareStatement(sql1);
				ps.execute();

				//fnTP, mnTP, lnTP, uTF, ppTF, pnTF, aTF, coTF, sopTF, cTF, zcTF; 
				//First name, Middle name, Last name, User name, Password, Phone Number, Address, Country, State or Province, City, Zip Code
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Connection Error! Please check your internet connection!", "Connection Error", 0);
				e1.printStackTrace();
		}
	}
	
	public void myFunction(Stage pane2, JavaDatabaseConnector newJDBC) {
		setStage(pane2);
		setConnection(newJDBC);
		try {
			setCon(connection.getCon());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JavaDatabaseConnector getConnection() {
		return connection;
	}

	public void setConnection(JavaDatabaseConnector passconnection) {
		connection = passconnection;	
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
