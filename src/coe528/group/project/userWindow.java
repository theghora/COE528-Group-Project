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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class userWindow extends singletonWindow {
    
    String title = "User Window";
    
    Text welcome_l;
    VBox vbox;
    
    Button logoutButton;
    Button buyButton;
    Button redeemButton;
    
    User user;
    Customer customer = new Customer("user", "user", 0);
    
    private static userWindow instance;
    
    private TableView<book> bookTable = new TableView<book>();
    bookHandler handler = new bookHandler();
    ArrayList<bookHandler.book> books = handler.getBookDB();
    ObservableList<book> data = FXCollections.observableArrayList();

    private userWindow(){        
        welcome_l = new Text("Welcome");

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
        selectCol.setCellValueFactory(new PropertyValueFactory<book,Boolean>("selected"));

        for (int i = 0; i < books.size(); i++) {
            data.add(books.get(i));
        }
         
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
            stage.setScene(new Scene(customerCostScreenBuy(), 400, 200));
            }
        });
        
        redeemButton = new Button();
        redeemButton.setText("Redeem points and Buy");
        redeemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            stage.setScene(new Scene(customerCostScreenRedeem(), 400, 200));
            }
        });

                
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
    
    public VBox customerCostScreenBuy() {
        double totalCost = 0;
        for(book b: data) {
            if(b.getSelected().equals(true)) {
                totalCost = (b.getPrice());
                b.setSelected(false);
            }
        }
        
        logoutButton = new Button();
        logoutButton.setText("Log Out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logged out");
                loginWindow.getInstance().show(stage);
            }
        });
        
        customer.buy(totalCost);
        Label TC = new Label("Total Cost: " + (int)totalCost);

        Label points_status = new Label("Points: "+ customer.getPoints() + ", " + "Status: " + customer.getStatus());

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(TC, points_status, logoutButton);
        vbox.setPadding(new Insets(50, 50, 50, 50));
        return vbox;
    }
    
    
    public VBox customerCostScreenRedeem() {
        double totalCost = 0;
        for(book b: data) {
            if(b.getSelected().equals(true)) {
                totalCost = totalCost + b.getPrice();
                b.setSelected(false);
            }
        }

        logoutButton = new Button();
        logoutButton.setText("Log Out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logged out");
                loginWindow.getInstance().show(stage);
            }
        });
        
        Label TC = new Label("Total Cost: " + (int)customer.redeemPointsBuy(totalCost));

        Label points_status = new Label("Points: " );

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(TC, points_status, logoutButton);
        vbox.setPadding(new Insets(50, 50, 50, 50));
        return vbox;
    }

}
