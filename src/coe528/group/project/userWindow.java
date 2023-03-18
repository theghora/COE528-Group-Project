/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class userWindow /*extends Stage*/ {
    //SINGLETON!!!!!!!
    //but not really
    //no it isn't
    //actually it is
    
    
    StackPane window;
    Text welcome_l;
    VBox vbox;
    Stage stage;

    
    private static userWindow instance;
    
    private userWindow(){
        //set up all the ui bs here
        welcome_l = new Text("perfectly safe for work test paragraph that certainly contains no bad words");
        vbox = new VBox();
        
        vbox.getChildren().add(welcome_l);
       
        window = new StackPane();       
        window.getChildren().add(vbox);
        
        Scene scene = new Scene(window, 300, 250);
        
        stage = new Stage();
        
        stage.setTitle("Bookstore App");
        stage.setScene(scene);        
    }
    
    public void show(){
        stage.show();
    }
    
    static userWindow getInstance(){
        if(instance != null){
            return instance;
        }else{
            instance = new userWindow();
            return getInstance();
        }
    }
    
}
