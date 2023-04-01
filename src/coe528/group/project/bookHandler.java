package coe528.group.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;

public class bookHandler {
    
    //test main
    public static void main(String[] args) {
        /*bookHandler test = new bookHandler();
        test.createBook(1600, "Don Quixote");
        test.createBook(1200, "Dream of the Red Chamber");
        test.createBook(900, "Moby Dick");
        test.export();*/
    }
    
    public class book {

        private String title;
        private int price;
        private CheckBox selected;

        private book(int p, String t){
            this.title = t;
            this.price = p;
            this.selected = new CheckBox();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public CheckBox getSelected() {
            return selected;
        }

        public void setSelected(CheckBox selected) {
            this.selected = selected;
        }
        
        @Override
        public String toString(){
            return ""+price+"#$"+title;
        }
    }
    
    ArrayList<book> bookDB;

    bookHandler(){
        bookDB = new ArrayList<book>();
        reload();
    }

    public boolean reload(){
        try {
            BufferedReader read = new BufferedReader(new FileReader("books.txt"));
            String s;
            while((s = read.readLine()) != null){
                createBook(Integer.parseInt(s.split("#\\$")[0]), s.split("#\\$")[1]);
            }
            read.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean export(){
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("books.txt", true));
            for(book x: bookDB){
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
    
    public void createBook(int p, String t){
        bookDB.add(new book(p,t));
    }
    
    public ArrayList<book> getBookDB(){
        //yes this exposes the rep now stfu
        return bookDB;
    }   
}
