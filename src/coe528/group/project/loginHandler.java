package coe528.group.project;

import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class loginHandler {

    private static final String CUSTOMERS_FILE_PATH = "customers.txt";

    public static boolean enter(String username, String password, Stage p){

        if(username.equals("admin") && password.equals("admin")){
            adminWindow.getInstance().show(p);
            return true;
        }

        if(isCustomer(username, password)){
            userWindow.getInstance().show(p);
            return true;
        }

        return false;
    }

    private static boolean isCustomer(String username, String password){
        try {
            File customersFile = new File(CUSTOMERS_FILE_PATH);
            Scanner scanner = new Scanner(customersFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if(fields.length == 3 && fields[1].equals(username) && fields[2].equals(password)){
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }
}

