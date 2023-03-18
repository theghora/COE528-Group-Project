/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class COE528GroupProject extends Application {
    
    static Stage p;
    
    @Override
    public void start(Stage primaryStage) {
   
        //suffixing strings with "_l"
        Text username_l = new Text("Username");
        TextField username = new TextField();
        Text password_l = new Text("Password");
        PasswordField password = new PasswordField();
        
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("attempted login with username: \"" + username.getText() + "\" password: \""+ password.getText()+"\"");
                loginHandler.enter(username.getText(), password.getText()); 
            }
        });
        
        p = primaryStage;
        
        VBox vbox = new VBox();
        
        vbox.setAlignment(Pos.CENTER);
        
        vbox.setPadding(new Insets(16));
        vbox.setSpacing(16);
        
        vbox.getChildren().add(username_l);
        vbox.getChildren().add(username);
        vbox.getChildren().add(password_l);
        vbox.getChildren().add(password);
        vbox.getChildren().add(loginButton);
        
        
        StackPane root = new StackPane();    
        root.getChildren().add(vbox);
        //root.getChildren().add(username);
       // root.getChildren().add(loginButton);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
