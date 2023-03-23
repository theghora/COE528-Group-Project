/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

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
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class loginWindow implements showable {
    
    private static loginWindow instance;
    
    Text username_l, password_l;
    TextField username;
    PasswordField password;
    Button loginButton;
    StackPane root;
    Scene scene;
    Stage stage;
    
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
                loginHandler.enter(username.getText(), password.getText(), stage); 
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
    

    @Override
    public void show(Stage p) {
        p.setTitle("Login Window");
        p.setScene(scene);
        stage = p;
    }
    
}
