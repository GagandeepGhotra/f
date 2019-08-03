package fray.GUIs.ControllerClasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import fray.JDBC.JavaDatabaseConnector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginClassController  {
	@FXML 
	private JFXTextField uTF;
	
	@FXML
	private ImageView close;
	
	@FXML
	private JFXPasswordField pTF;

	@FXML
	private JFXButton login;
	
	@FXML 
	private Pane content_area;
	
	@FXML
	private Stage stage;

	private JavaDatabaseConnector connection = getConnection();
	private Connection con;
	
	@FXML
	void closeApplication() {
		try {
			connection.getCon().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.exit();
	}

	@FXML
	void openNewStage(ActionEvent event) {
			try {
				PreparedStatement ps;
				ps = con.prepareStatement("Select * from " + getConnection().getDatabaseUsername() + ".UserDataTable where Username = ? and Password = ?");
				ps.setString(1, uTF.getText());
				ps.setString(2, String.valueOf(pTF.getText()));
				ResultSet result = ps.executeQuery();
				if(result.next()){
					 /*
	       			 * This part of code updates the online status on the server.
	       			 */
	       			java.sql.Statement myStat;
	    			myStat = con.createStatement();
	    		    String sql = "UPDATE " + getConnection().getDatabaseUsername() + ".UserDataTable SET OnlineStatus = 'Online' WHERE Username = '" + uTF.getText() + "'";
	       			myStat.executeUpdate(sql);
	       			 
					FXMLLoader root = new FXMLLoader(getClass().getResource("/GUIs/FXML/MainPage.fxml"));
					Parent root1 = (Parent) root.load();
				
					MainPageController sec = root.getController();
					Stage w = (Stage) ((Node)event.getTarget()).getScene().getWindow();
					//w.setMaximized(true);
					Screen screen = Screen.getPrimary();
					Rectangle2D bounds = screen.getVisualBounds();
//		
					w.setX(bounds.getMinX());
					w.setY(bounds.getMinY());
					w.setWidth(bounds.getWidth());
					w.setHeight(bounds.getHeight() + 40);//;//////////////////////////////////////////////////////////////////////////////////////////
				System.out.println(w.getX() + " " + w.getY() + " " + w.getWidth() + " " + w.getHeight());
					sec.passConnectionAndUsername(uTF.getText(), getConnection(), w.getX(), w.getY(), w.getWidth(), w.getHeight());
					
					w.setScene(new Scene(root1));

					w.show();
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Invalid username or password");
					uTF.setText("");
					pTF.setText("");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
   //	}
	
	@FXML
    private void mousePressed(ActionEvent event) throws NullPointerException{		
			try {
				FXMLLoader root =  new FXMLLoader(getClass().getResource("/GUIs/FXML/Registration.fxml"));
				Parent root1 = (Parent) root.load();
				
				RegistrationClass sec = root.getController();
				sec.myFunction(getStage(), getConnection());
				content_area.getChildren().removeAll();
				content_area.getChildren().setAll(root1);
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	
	public void myFunction(JavaDatabaseConnector newJDBC) {
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
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}