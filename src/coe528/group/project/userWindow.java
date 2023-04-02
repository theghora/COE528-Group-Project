package coe528.group.project;

import coe528.group.project.bookHandler.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class userWindow extends singletonWindow {
    
    String title = "User Window";
    
    Text welcome;
    VBox vbox;
    
    Button logoutButton;
    Button buyButton;
    Button redeemButton;
    
    private static userWindow instance;
    
    private TableView<book> bookTable = new TableView<book>();
    
    bookHandler handler = new bookHandler();
    ObservableList<book> data = FXCollections.observableArrayList(handler.getBookDB());

    Customer customer = loginHandler.getInstance().getCurrentCustomer();
    
    private userWindow(){     
        
        welcome = new Text("Welcome " + customer.getUsername() + ". You have " + customer.getPoints() + " points. Your status is " + customer.getStatus() + ".");
        welcome.setFont(new Font("Times New Roman", 15));

        bookTable.setEditable(true);
        
        TableColumn nameCol = new TableColumn("Book Name");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(new PropertyValueFactory<book,String>("title"));

        TableColumn priceCol = new TableColumn("Book Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<book,Integer>("price"));

        TableColumn selectCol = new TableColumn("Select");
        selectCol.setMinWidth(25);
        selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));
        selectCol.setCellValueFactory(new PropertyValueFactory<book,CheckBox>("checkBox"));
      
         
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        bookTable.setItems(data);
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
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            stage.setScene(new Scene(customerCostScreenBuy(e), 400, 200));
            }
        });
        
        redeemButton = new Button();
        redeemButton.setText("Redeem points and Buy");
        redeemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            stage.setScene(new Scene(customerCostScreenRedeem(e), 400, 200));
            }
        });
        
        vbox = new VBox();
        
        // Alignment and Spacing
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(16));
        vbox.setSpacing(16);
        
        vbox.getChildren().add(welcome);
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
        if(instance != null)
            return instance;
        else{
            instance = new userWindow();
            return getInstance();
        }
    }
    
    public VBox customerCostScreenBuy(ActionEvent Event) {

        double totalCost = 0;
        for(book b: data) {
            if(b.getSelected().isSelected()) {
               System.out.println("hsfjhaifdiajn");
               b.checkboxFire();
            }
        System.out.println("\t"+String.valueOf(totalCost));
        customer.buy(totalCost);
        Label TC = new Label("Total Cost: " + (int)totalCost);

        Label points_status = new Label("Points: "+ customer.getPoints() + ", " + "Status: " + customer.getStatus());

        logoutButton = new Button();
        logoutButton.setText("Log Out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logged out");
                loginWindow.getInstance().show(stage);
            }
        });
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(TC, points_status, logoutButton);
        vbox.setPadding(new Insets(50, 50, 50, 50));
        return vbox;
    }
        return vbox;
    }
    
    public VBox customerCostScreenRedeem(ActionEvent Event) {
        double totalCost = 0;
        for(book b: data) {
            if(b.getSelected().isSelected()) {
                System.out.println("Selected");
                b.checkboxFire();
            }
        }
         System.out.println("\t"+String.valueOf(totalCost));
        Label TC = new Label("Total Cost: " + (int)customer.redeemPointsBuy(totalCost));

        Label points_status = new Label("Points: " + customer.getPoints() + ", " + "Status: " + customer.getStatus());

        logoutButton = new Button();
        logoutButton.setText("Log Out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logged out");
                loginWindow.getInstance().show(stage);
            }
        });
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(TC, points_status, logoutButton);
        vbox.setPadding(new Insets(50, 50, 50, 50));
        return vbox;
    }
    

}
