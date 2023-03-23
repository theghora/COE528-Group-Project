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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class adminWindow implements showable /*extends Stage*/ {
    //SINGLETON!!!!!!!
    //but not really
    //no it isn't
    //actually it is
    
    
    StackPane window;
    Text welcome_l;
    VBox vbox;
    Scene scene;
    Stage stage;
    
    Button logoutButton;

    
    private static adminWindow instance;
    
    private adminWindow(){
        //set up all the ui bs here
        welcome_l = new Text("Congrat! Yo discover admin window!");
        
        logoutButton = new Button();
        logoutButton.setText("Log Out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logged out");
                loginWindow.getInstance().show(stage);
            }
        });
        
        vbox = new VBox();
        
        //Alignment and Spacing
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(16));
        vbox.setSpacing(16);
        
        vbox.getChildren().add(welcome_l);
        vbox.getChildren().add(logoutButton);
       
        window = new StackPane();       
        window.getChildren().add(vbox);
        
        scene = new Scene(window, 800, 600);
           
    }
    
    public void show(Stage p){
        p.setTitle("Admin Window");
        p.setScene(scene);
        stage = p;
    }
    
    static adminWindow getInstance(){
        if(instance != null){
            return instance;
        }else{
            instance = new adminWindow();
            return getInstance();
        }
    }
    
}

