package coe528.group.project;

java.io.BufferedReader
java.io.FileReader
java.io.FileNotFoundException
java.io.IOException
java.util.StringTokenizer

public void loadData() throws FileNotFoundException, IOException{

    try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
        String line;
        String book = new String();
        String price = new String();
        int counter = 0;
        while ((line = br.readLine()) != null) {
            if ( counter == 0 ){
                counter += 1; 
            } else {
                String[] parts = line.split(", ");
                book = parts[0];
                price = parts[1];
                observableList_books.add(new Book(book, price));
            }
        }
    }

    try (BufferedReader br = new BufferedReader(new FileReader("customers.txt"))) {
        String line;
        String username = new String();
        String password = new String();
        String points = new String();
        int counter = 0;
        while ((line = br.readLine()) != null) {
            if ( counter == 0 ){
                counter += 1;
            } else {
                String[] parts = line.split(", ");
                username = parts[0];
                password = parts[1];
                points = parts[2];
                // check if customer exists in text file
                if (username.equals(person.getUsername()) && password.equals(person.getPassword())) {
                    observableList_customers.add(new Customer(username, password, Integer.parseInt(points)));
                }
            }
        }
    }

}
