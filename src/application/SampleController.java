package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

//import database.DBConnection;
import javafx.collections.ObservableList;
//import database.DBConnection;
//import framePanel.Start;
//import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	
		
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btnEnter;

    @FXML
    private Button btnGetStarted;

    @FXML
    private Button btnSignUp;
    
    @FXML
    private Button btnGoBack;

    @FXML
    private Button click;

    @FXML
    private ImageView myImage;

    @FXML
    private TextField textContact;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textName;

    @FXML
    private TextField textName1;

    @FXML
    private PasswordField textPassword;

    @FXML
    private PasswordField textPassword1;

    @FXML
    private Pane panelSignIn;

    @FXML
    private Pane panelSignUp;
    
    @FXML
    private Label lblGoBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
	public void goSignUp(ActionEvent event) {
		panelSignUp.toFront();
	}
	public void goSignIn(ActionEvent event) {
		panelSignIn.toFront();
	}
	
	public void enter(ActionEvent e) throws IOException {
		try  {
		      Connection con=DBConnection.getConnection();
		      PreparedStatement pst=con.prepareStatement("SELECT * FROM user WHERE name=? AND password=?");
		      
		      pst.setString(1,textName.getText());
		      pst.setString(2,textPassword.getText());
		      
		      ResultSet result=pst.executeQuery();
		 				 		
		      if (result.next()) {
		    	  	print("Успішно", "Title");
		    	  	switch2(e);
		      } else print("Помилка", "Title");
		      
	       } catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public void registration(ActionEvent e) throws IOException {
		try  {
		      Connection con=DBConnection.getConnection();
		      PreparedStatement pst=con.prepareStatement("INSERT INTO user(name, password, email, contact) VALUES (?,?,?,?)");
		      
		      pst.setString(1,textName1.getText());
		      pst.setString(2,textPassword1.getText());
		      pst.setString(3,textEmail.getText());
		      pst.setString(4,textContact.getText());
		      
		      int rowCount = pst.executeUpdate();
		      
		      
		      if (rowCount>0) {
		               JOptionPane.showMessageDialog(null, "Успішна реєстрація працівника");
		      } else JOptionPane.showMessageDialog(null, "Працівник не добавленний");
		      
	       } catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	
	public void switch2(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sample2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // Apply styles

		stage.setScene(scene);
		stage.show();
	}
	
	public void click(ActionEvent event){
        print(textName.getText(), "window");
        try  {
		      Connection con=DBConnection.getConnection();
		      PreparedStatement pst=con.prepareStatement("INSERT INTO testtable(word) VALUES (?);");
		      
		      pst.setString(1, textName.getText());
	
		      
		      int rowCount = pst.executeUpdate();
		      

		      
	       } catch (SQLException e) { e.printStackTrace(); }
    }
    private void print(String contentText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
