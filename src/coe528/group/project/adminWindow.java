/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import coe528.group.project.bookHandler.book;
import java.util.ArrayList;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author super
 */
public class adminWindow extends singletonWindow {

    String title = "Admin Window";

    Text welcome_l;
    VBox vbox;
    Button books, customers, logoutButton;

    private static adminWindow instance;
    
    private adminWindow(){
        
        
        //set up all the ui bs here
        welcome_l = new Text("Please select what you wish to edit.");
        
        books = new Button();
        books.setText("Books");
        books.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                adminBookWindow.getInstance().show(stage);
                System.out.println("books button clicked");
                //do something
            }
        });

        customers = new Button();
        customers.setText("Customers");
        customers.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                adminUserWindow.getInstance().show(stage);
                System.out.println("customers button clicked");
                //do something
            }
        });

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
        vbox.getChildren().add(books);
        vbox.getChildren().add(customers);

        vbox.getChildren().add(logoutButton);

        window = new StackPane();
        window.getChildren().add(vbox);

        scene = new Scene(window, 300, 250);

        /*         //if(instance !=null){           //if window is closed from admin window
            stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,
                    this::closeWindowEvent);        
            }
              public void closeWindowEvent(WindowEvent event) {              //deletes contents of books and customers files
        System.out.println("Window close request ...");
        try {
            FileWriter C = new FileWriter("customers.txt",false);
            PrintWriter Cw = new PrintWriter(C, false);
            FileWriter B = new FileWriter("books.txt",false);
            PrintWriter Bw = new PrintWriter(B, false);
            Cw.flush();
            Cw.close();
            C.close();
            Bw.flush();
            Bw.close();
            B.close();
        }catch(Exception e){
           e.printStackTrace();
        }
            } */
    }

    static adminWindow getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new adminWindow();
            return getInstance();
        }
    }

}


class adminBookWindow extends singletonWindow {
    
    VBox mainVertical, subVertical;
    HBox bottomButtons;
    ArrayList<HBox> listItems;
    Button delete,back,add;
    TableView table;
    bookHandler handler = new bookHandler();
    ObservableList<bookHandler.book> data;

    
    String title = "Book Window";
    
    private static adminBookWindow instance;
    
    private adminBookWindow(){
        
        ObservableList<bookHandler.book> data = FXCollections.observableArrayList(handler.getBookDB());

        mainVertical = new VBox();
        subVertical = new VBox();
        bottomButtons = new HBox();
        
        
        table = new TableView();
        table.setEditable(true);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn priceCol = new TableColumn("Price");
        
        titleCol.setCellValueFactory( new PropertyValueFactory<book, String>("title") );
        priceCol.setCellValueFactory( new PropertyValueFactory<book, Integer>("price") );
        
        table.setItems(data);
        table.getColumns().addAll(titleCol, priceCol);
        
        subVertical.getChildren().add(table);
        
        back = new Button();
        
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                adminWindow.getInstance().show(stage);
                System.out.println("back button clicked");
                //do something
            }
        });
        back.setMinWidth(60);
        
        bottomButtons.getChildren().add(back);
        
        Region spacer = new Region();
        spacer.setPrefWidth(800);
        bottomButtons.getChildren().add(spacer);

        
        delete = new Button();
        
        delete.setText("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("delete button clicked");
                //do something
            }
        });
        delete.setMinWidth(60);
        
        bottomButtons.getChildren().add(delete);
        
        mainVertical.getChildren().add(subVertical);
        mainVertical.getChildren().add(bottomButtons);
        
        window = new StackPane();       
        window.getChildren().add(mainVertical);
        
        scene = new Scene(window, 300, 250);
    }
    
    static adminBookWindow getInstance(){
        if(instance != null){
            return instance;
        }else{
            instance = new adminBookWindow();
            return getInstance();
        }
    }
    
}

class adminUserWindow extends singletonWindow {
    
    VBox mainVertical, subVertical;
    HBox bottomButtons;
    ArrayList<HBox> listItems;
    Button delete,back,add;
    TableView table;
    loginHandler handler = loginHandler.getInstance();
    ObservableList<User> data;

    
    String title = "Book Window";
    
    private static adminUserWindow instance;
    
    private adminUserWindow(){
        
        ObservableList<User> data = FXCollections.observableArrayList(handler.getUserDB());

        mainVertical = new VBox();
        subVertical = new VBox();
        bottomButtons = new HBox();
        
        
        table = new TableView();
        table.setEditable(true);
        TableColumn nameCol = new TableColumn("Username");
        TableColumn passwordCol = new TableColumn("Password");
        
        nameCol.setCellValueFactory( new PropertyValueFactory<User, String>("username") );
        passwordCol.setCellValueFactory( new PropertyValueFactory<User, String>("password") );
        
        table.setItems(data);
        table.getColumns().addAll(nameCol, passwordCol);
        
        subVertical.getChildren().add(table);
        
        back = new Button();
        
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                adminWindow.getInstance().show(stage);
                System.out.println("back button clicked");
                //do something
            }
        });
        back.setMinWidth(60);
        
        bottomButtons.getChildren().add(back);
        
        Region spacer = new Region();
        spacer.setPrefWidth(800);
        bottomButtons.getChildren().add(spacer);

        
        delete = new Button();
        
        delete.setText("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("delete button clicked");
                //do something
            }
        });
        delete.setMinWidth(60);
        
        bottomButtons.getChildren().add(delete);
        
        mainVertical.getChildren().add(subVertical);
        mainVertical.getChildren().add(bottomButtons);
        
        window = new StackPane();       
        window.getChildren().add(mainVertical);
        
        scene = new Scene(window, 300, 250);
    }
    
    static adminUserWindow getInstance(){
        if(instance != null){
            return instance;
        }else{
            instance = new adminUserWindow();
            return getInstance();
        }
    }
    
}

