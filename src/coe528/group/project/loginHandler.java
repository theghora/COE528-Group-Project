package coe528.group.project;

import java.io.BufferedReader;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class loginHandler {

    private static final String CUSTOMERS_FILE_PATH = "customers.txt";
    
    private static loginHandler instance;
    private static Customer customer;
    private ArrayList<User> users;

    private loginHandler() {
        this.reload();
    }
    
    public static boolean enter(String username, String password, Stage p){

        if(username.equals("admin") && password.equals("admin")){
            adminWindow.getInstance().show(p);
            return true;
        }

        if(isCustomer(username, password)){
            customer = new Customer(username, password, customer.getPoints());
            userWindow.getInstance().show(p);
            
            return true;
        }

        return false;
    }
    
    public Customer getCurrentCustomer() {
        return customer;
    }

    private static boolean isCustomer(String username, String password){
        try {
            File customersFile = new File(CUSTOMERS_FILE_PATH);
            Scanner scanner = new Scanner(customersFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if(fields.length == 3 && fields[1].equals(username) && fields[2].equals(password)){
                    customer = new Customer(username, password, Integer.parseInt(fields[0]));
  
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static loginHandler getInstance() {
        if (instance == null)
            instance = new loginHandler();
        return instance;
    }
    
    boolean reload(){
        users = new ArrayList<User>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(CUSTOMERS_FILE_PATH));
            String s;
            while((s = read.readLine()) != null){
                users.add(new Customer(s.split(",")[1],s.split(",")[2],Integer.parseInt(s.split(",")[0])));
            }
            read.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<User> getUserDB(){
        //yes this exposes the rep now stfu
        return users;
    }
}
 
