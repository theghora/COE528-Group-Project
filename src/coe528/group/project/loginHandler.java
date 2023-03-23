/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import javafx.stage.Stage;

/**
 *
 * @author super
 */
public class loginHandler {
    //this class literally just contains a method which takes a username and
    //password then opens up either userWindow or adminWindow depending on if
    //the target account is a user or an admin
    
    //or error out I guess but that's to be added later
    
    //because its easier to have separate classes for the user's window and the
    //admin's window than make a single class adapt
    
    public static boolean enter(String username, String password, Stage p){
        
        if(username.equals("user")){
            //do something
            userWindow.getInstance().show(p);
        }else if(username.equals("admin")){
            //do something else
            adminWindow.getInstance().show(p);
        }
        
        return true;
    }
}
