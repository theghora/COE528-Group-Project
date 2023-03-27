/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import coe528.group.project.bookHandler.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class userWindow extends singletonWindow {
    
    String title = "User Window";
    
    Text welcome_l;
    VBox vbox;
    
    Button logoutButton;
    Button buyButton;
    Button redeemButton;
    
    User user;
    
    private static userWindow instance;
    
    private TableView<bookHandler> bookTable = new TableView<bookHandler>();
    private ObservableList<bookHandler> books = FXCollections.observableArrayList(
            new bookHandler(), new bookHandler(), new bookHandler());
    
    private userWindow(){
        //set up all the ui bs here
        
        welcome_l = new Text("Welcome");

        TableColumn nameCol = new TableColumn("Book Name");
        nameCol.setMinWidth(450);
        nameCol.setCellValueFactory(new PropertyValueFactory<bookHandler,String>("bookName"));

        TableColumn priceCol = new TableColumn("Book Price");
        priceCol.setMinWidth(200);
        priceCol.setCellValueFactory(new PropertyValueFactory<bookHandler,String>("bookPrice"));

        TableColumn selectCol = new TableColumn("Select");
        selectCol.setMinWidth(100);
        selectCol.setCellValueFactory(new PropertyValueFactory<bookHandler,String>("checkBox"));//change string

        bookTable.setItems(books);
        bookTable.getColumns().addAll(nameCol, priceCol, selectCol);

        logoutButton = new Button();
        logoutButton.setText("Log Out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logged out");
                loginWindow.getInstance().show(stage);
            }
        });
        
        buyButton = new Button();
        buyButton.setText("Buy");
        
        
        redeemButton = new Button();
        redeemButton.setText("Redeem points and Buy");
        
        
        vbox = new VBox();
        
        //Alignment and Spacing
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(16));
        vbox.setSpacing(16);
        
        vbox.getChildren().add(welcome_l);
        vbox.getChildren().add(bookTable);
        vbox.getChildren().add(buyButton);
        vbox.getChildren().add(redeemButton);
        vbox.getChildren().add(logoutButton);


        window = new StackPane();       
        window.getChildren().add(vbox);
        
        scene = new Scene(window, 800, 600);
                    
    }
    
    public void show(Stage p){
        p.setTitle("User Window");
        p.setScene(scene);
        stage = p;
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
