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

    // assuming the method loadData() is defined somewhere in the class
    private void loadData() throws IOException {
        // code to load data from files
    }
}
