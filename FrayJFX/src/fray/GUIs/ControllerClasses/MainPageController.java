package fray.GUIs.ControllerClasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import fray.JDBC.JavaDatabaseConnector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
//import ObjectClasses.Player;

public class MainPageController {
	
	@FXML
	private JFXButton exit;
	
	@FXML
	private AnchorPane switchPane;
	
	private JavaDatabaseConnector connection = getConnection();
	private Connection con;
	private String playerUsername;
	
	private double x, y, width, height;
	
	@FXML
	void exit(ActionEvent event) {
		try {
  			java.sql.Statement myStat;
			myStat = con.createStatement();
			String sql = "UPDATE " + getConnection().getDatabaseUsername() + ".UserDataTable SET OnlineStatus = 'Offline', InGame = 'No', LookingForAGame = 'No' WHERE Username = '" + getPlayerUsername() + "'";
			myStat.executeUpdate(sql);
			getConnection().getCon().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.exit();
	}

	public void passConnectionAndUsername(String playerUsername, JavaDatabaseConnector passConnection, double x, double y, double width, double height) {
		setPlayerUsername(playerUsername);
		setConnection(passConnection);
		try {
			setCon(connection.getCon());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	@FXML
	void playGame(ActionEvent event) { // Leads the user to the race selection screen.	
		try {
			FXMLLoader root = new FXMLLoader(getClass().getResource(("/GUIs/FXML/MainPage-CharacterSelectScreenVersion2.fxml")));
			Parent root1 = (Parent) root.load();
		//	MainPageCharacterScreenControllerVersion2 sec = root.getController();
		//	sec.passConnectionAndUsername(getPlayerUsername(), getConnection());
			Stage w = (Stage) ((Node)event.getTarget()).getScene().getWindow();

//			switchPane.setPrefWidth(getWidth());
//			switchPane.setPrefHeight(getHeight());
//			switchPane.setLayoutX(getX());
//			switchPane.setLayoutY(getY());
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
//
			/*switchPane.setLayoutX(bounds.getMinX());
			switchPane.setLayoutY(bounds.getMinY());
			switchPane.setPrefWidth(bounds.getWidth());
			switchPane.setPrefHeight(bounds.getHeight() + 40);
			System.out.println(switchPane.getLayoutX() + " " + switchPane.getLayoutY() + " " + switchPane.getWidth() + " " + switchPane.getHeight());
			switchPane.getChildren().remove(0);
			switchPane.getChildren().addAll(root1);*/
			w.setX(bounds.getMinX());
			w.setY(bounds.getMinY());
			w.setWidth(bounds.getWidth());
			w.setHeight(bounds.getHeight() + 40);//;/////////
			w.setScene(new Scene(root1));

			w.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public JavaDatabaseConnector getConnection() {
		return connection;
	}

	public void setConnection(JavaDatabaseConnector connection) {
		this.connection = connection;
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public String getPlayerUsername() {
		return playerUsername;
	}

	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
}