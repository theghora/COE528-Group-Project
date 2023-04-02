package coe528.group.project;

import coe528.group.project.bookHandler.book;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class userWindow extends singletonWindow {
    
    String title = "User Window";
    
    Text welcome;
    VBox vbox;
    
    Button logoutButton;
    Button buyButton;
    Button redeemButton;
    Button back;
    
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

        TableColumn priceCol = new TableColumn("Book Price ($)");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<book,Integer>("price"));

        TableColumn selectCol = new TableColumn("Select");
        selectCol.setMinWidth(25);
        selectCol.setCellValueFactory(new PropertyValueFactory<book,CheckBox>("selected"));
        selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

        selectCol.setCellValueFactory(
            new Callback<CellDataFeatures<book,Boolean>,ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(CellDataFeatures<book, Boolean> b) {
                    return b.getValue().getSelected().selectedProperty();
                }
            });
         
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
                stage.setScene(new Scene(customerCostScreenBuy(), 600, 400));
            }
        });
        
        redeemButton = new Button();
        redeemButton.setText("Redeem points and Buy");
        redeemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stage.setScene(new Scene(customerCostScreenRedeem(), 600, 400));
            }
        });
        
              
    }
    
    public void show(Stage p){
        p.setTitle("User Window");
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
    
    public VBox customerCostScreenBuy() {
        double totalCost = 0;
        for(book b: data) {
            if(b.getSelected().isSelected()) {
                totalCost += b.getPrice();
                b.getSelected().setSelected(false);
            }
        }
        
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
        
        back = new Button();
        
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                welcome = new Text("Welcome " + customer.getUsername() + ". You have " + customer.getPoints() + " points. Your status is " + customer.getStatus() + ".");

                userWindow.getInstance().show(stage);

                System.out.println("back button clicked");
            }
        });
        
        

        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(TC, points_status, logoutButton,back);
        vbox.setPadding(new Insets(50, 50, 50, 50));
        return vbox;
    }
    
    public VBox customerCostScreenRedeem() {
        double totalCost = 0;
        for(book b: data) {
            if(b.getSelected().isSelected()) {
                totalCost += b.getPrice();
                b.getSelected().setSelected(false);
            }
        }
        Label TC = new Label("Total Cost: "+totalCost);
        Label Before_points_status = new Label("Before Points: " + customer.getPoints() + ", " + " Before Status: " + customer.getStatus());
        Label transaction = new Label("Transaction Cost: "+customer.redeemPointsBuy(totalCost));
        Label Current_points_status = new Label("After Points: " + customer.getPoints() + ", " + " After Status: " + customer.getStatus());

        logoutButton = new Button();
        logoutButton.setText("Log Out");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logged out");
                loginWindow.getInstance().show(stage);
            }
        });
        back = new Button();
        
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                welcome = new Text("Welcome " + customer.getUsername() + ". You have " + customer.getPoints() + " points. Your status is " + customer.getStatus() + ".");

                userWindow.getInstance().show(stage);

                System.out.println("back button clicked");
            }
        });
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(TC,transaction,Before_points_status,Current_points_status, logoutButton,back);
        vbox.setPadding(new Insets(50, 50, 50, 50));
        return vbox;
        
    }
}