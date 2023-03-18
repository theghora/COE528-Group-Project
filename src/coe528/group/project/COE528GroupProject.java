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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class COE528GroupProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button loginButton = new Button();
        loginButton.setText("Say 'Hello World'");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        TextField username = new TextField();
        TextField password = new TextField();
        
        VBox vbox = new VBox();
        
        StackPane root = new StackPane();
        
        vbox.getChildren().add(username);
        vbox.getChildren().add(password);
        vbox.getChildren().add(loginButton);
        
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
        System.out.println("git test");
    }
    
}
