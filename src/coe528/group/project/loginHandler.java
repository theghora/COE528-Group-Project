package coe528.group.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class loginHandler {

    private static final String CUSTOMERS_FILE_PATH = "customers.txt";
    
    private static loginHandler instance;
    private static Customer customer;
    private ArrayList<Customer> users;

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
        users = new ArrayList<Customer>();
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
    
    public void createBook(String u, String p, int po){
        users.add(new Customer(u,p,po));
    }
    
    public void removeBook(String u, String p, int po){
        int index = -1;
        for(Customer x: users){
                if (x.getUsername().equals(u)&&x.getPassword().equals(p)&&x.getPoints() == po){
                    index = users.indexOf(x);
                }
            }
        users.remove(users.get(index));
    }
    
    public void removeBook(Customer c){
        if(users.contains(c)){
            users.remove(c);
        }
    }
    
    public ArrayList<Customer> getUserDB(){
        //yes this exposes the rep now stfu
        return users;
    }

    boolean export() {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("customers.txt", false));
            for(Customer x: users){
                write.append(x.toString(), 0, x.toString().length());
                System.out.println(x.toString());
            }
            write.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
}
 
