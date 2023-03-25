/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author super
 */
public class bookHandler {
    
    class book{
        int price;
        String title;
        private book(int p, String t){
            title = t;
            price = p;
        }
        String getTitle(){
            return title;
        }
        String getPrice(){
            return title;
        }
        @Override
        public String toString(){
            return ""+price+" "+title;
        }
    }
    
    ArrayList<book> bookDB;
    
    public boolean reload(){
        try {
            BufferedReader read = new BufferedReader(new FileReader("books.txt"));
            String s;
            for(;;){
                s = read.readLine();
                s.split(" ", 1);
                if(s != null){
                System.out.println(s);
                }else{
                    break;
                }
            }
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
            }
            write.close();
            return true;
        // Write the code here
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
    
    public void createBook(int p, String t){
        bookDB.add(new book(p,t));
    }
}
