package coe528.group.project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class loginWindow extends singletonWindow {
    
    private static loginWindow instance;
    
    String title = "Login Window";
    
    Text username_l, password_l;
    TextField username;
    PasswordField password;
    Button loginButton, closeButton;
    
    private loginWindow(){
        //suffixing strings with "_l"
        username_l = new Text("Username");
        username = new TextField();
        password_l = new Text("Password");
        password = new PasswordField();
        
        loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction((ActionEvent event) -> {
            System.out.println("attempted login with username: \"" + username.getText() + "\" password: \""+ password.getText()+"\"");
            loginHandler.enter(username.getText(), password.getText(), stage);
        });
        
        closeButton = new Button();
        closeButton.setText("Exit");
        closeButton.setOnAction((ActionEvent event) -> {
            System.out.println("Goodbye!");
            System.exit(0);
        });
        
        //creating a horizontal box to hold both buttons
        HBox hbox = new HBox();
        
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(16);
        
        hbox.getChildren().add(loginButton);
        hbox.getChildren().add(closeButton);
        
        //creating a vertical box which will hold most of the objects
        VBox vbox = new VBox();
        
        vbox.setAlignment(Pos.CENTER);
        
        vbox.setPadding(new Insets(16));
        vbox.setSpacing(16);
        
        vbox.getChildren().add(username_l);
        vbox.getChildren().add(username);
        vbox.getChildren().add(password_l);
        vbox.getChildren().add(password);
        vbox.getChildren().add(hbox);
        
        
        window = new StackPane();    
        window.getChildren().add(vbox);
        
        scene = new Scene(window, 300, 250);
        
    }
    
    static loginWindow getInstance(){
        if(instance != null){
            return instance;
        }else{
            instance = new loginWindow();
            return getInstance();
        }
    }
    
}
