package coe528.group.project;

import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * author super
 */
public class loginHandler {
    //this class literally just contains a method which takes a username and
    //password then opens up either userWindow or adminWindow depending on if
    //the target account is a user or an admin

    //or error out I guess but that's to be added later

    //because its easier to have separate classes for the user's window and the
    //admin's window than make a single class adapt
    
    // file path to the customers text file
    private static final String CUSTOMERS_FILE_PATH = "customers.txt";
    
    public static boolean enter(String username, String password, Stage p){
        
        // check if the customer is real by checking if their username
        // is in the customers text file
        if(isCustomer(username, password)){
            if(username.equals("user")){
                userWindow.getInstance().show(p);
            }else if(username.equals("admin") && password.equals("admin")){
                adminWindow.getInstance().show(p);
            } else {
                // invalid password
                return false;
            }
        } else {
            // customer not found
            return false;
        }
        
        return true;
    }
    
    // helper method to check if a customer is real by checking if their
    // username and password are in the customers text file
    private static boolean isCustomer(String username, String password){
        try {
            File customersFile = new File(CUSTOMERS_FILE_PATH);
            Scanner scanner = new Scanner(customersFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if(fields.length == 3 && fields[1].equals(username) && fields[2].equals(password)){
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}

