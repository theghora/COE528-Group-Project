package coe528.group.project;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginHandler implements EventHandler<WindowEvent> {

    @Override
    public void handle(WindowEvent e) {
        try {
            // assuming the method loadData() is defined in the same class
            loadData();
        } catch (FileNotFoundException ex) {
            System.out.println("" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("" + ex.getMessage());
        }

        // assuming observableList_customers is defined somewhere in the class
        boolean customerExists = false;
        for (Customer c : observableList_customers) {
            // assuming the variables person, getUsername(), and getPassword() are defined in the Customer class
            if (c.getUsername().equals(person.getUsername()) && c.getPassword().equals(person.getPassword())) {
                customerExists = true;
                break;
            }
        }

        if (customerExists) {
            System.out.println("Customer exists.");
            // handle successful login
        } else {
            System.out.println("Customer does not exist.");
            // display error message
        }
    }

    private static boolean isCustomer(String username, String password){
        try {
            File customersFile = new File(CUSTOMERS_FILE_PATH);
            try (Scanner scanner = new Scanner(customersFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] fields = line.split(",");
                    if(fields.length == 3 && fields[1].equals(username) && fields[2].equals(password)){
                        scanner.close();
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
           
        }
        return false;
    }
}
