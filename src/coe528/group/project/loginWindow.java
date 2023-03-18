/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import static coe528.group.project.COE528GroupProject.p;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author super
 */
public class loginWindow {
    
    private static loginWindow instance;
    
    Text username_l, password_l;
    TextField username;
    PasswordField password;
    Button loginButton;
    StackPane root;
    Scene scene;
    
    private loginWindow(){
        //suffixing strings with "_l"
        username_l = new Text("Username");
        username = new TextField();
        password_l = new Text("Password");
        password = new PasswordField();
        
        loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("attempted login with username: \"" + username.getText() + "\" password: \""+ password.getText()+"\"");
                loginHandler.enter(username.getText(), password.getText()); 
            }
        });
                
        VBox vbox = new VBox();
        
        vbox.setAlignment(Pos.CENTER);
        
        vbox.setPadding(new Insets(16));
        vbox.setSpacing(16);
        
        vbox.getChildren().add(username_l);
        vbox.getChildren().add(username);
        vbox.getChildren().add(password_l);
        vbox.getChildren().add(password);
        vbox.getChildren().add(loginButton);
        
        
        root = new StackPane();    
        root.getChildren().add(vbox);
        //root.getChildren().add(username);
       // root.getChildren().add(loginButton);
        
        scene = new Scene(root, 300, 250);
        
    }
    
    static loginWindow getInstance(){
        if(instance != null){
            return instance;
        }else{
            instance = new loginWindow();
            return getInstance();
        }
    }
    
    public void show(){
        COE528GroupProject.p.setTitle("Login Window");
        COE528GroupProject.p.setScene(scene);    
    }
    
}
