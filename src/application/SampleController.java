package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	
		
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label labelHello;
	
	@FXML
	private TextField textName;
	
	@FXML
	private TextField textPassword;
	
	@FXML
	private Button btnEnter;
	
//	@FXML
//	private Button testBtn;
//	
//	@FXML
//	private Button click;
//	
//	@FXML
//	private ImageView myImage;
//	
//	@FXML
//    private TableView<Users> tableUser;
//	
//    @FXML
//    private TableColumn<Users, String> colName;
//    
//    @FXML
//    private TableColumn<Users, String> colPassword;
//    
//    @FXML
//    private TableColumn<Users, String> colEmail;
//    
//    @FXML
//    private TableColumn<Users, String> colContact;
//
//    
//    ObservableList<Users> listM;
//    int index = -1;
//    
//    Connection con = null;
//    ResultSet rs = null;
//    PreparedStatement pst = null;
//    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    	colName.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
//    	colPassword.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));
//    	colEmail.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
//    	colContact.setCellValueFactory(new PropertyValueFactory<Users, String>("contact"));
//    	
//    	listM = DBConnection.getDataUsers();
//    	tableUser.setItems(listM);
    }
    
	public void testF(ActionEvent event) {
		System.out.println("dshfkdshfkdsfkjs");
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
	
	public void switch1(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // Apply styles

		stage.setScene(scene);
		stage.show();
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
