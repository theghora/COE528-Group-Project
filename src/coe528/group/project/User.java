/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

/**
 *
 * @author super
 */
abstract class User {
    private String username;
    private String password;
    
    public User(String u, String p){
        this.username = u;
        this.password = u;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setUsername(String u){
        this.username = u;
    }
    
    public void setPassword(String u){
        this.password = u;
    }

}
    class Customer extends User {
        
        protected int points;
        
        protected Status status;
        
        enum Status {
            SILVER,
            GOLD
        }
        
        public Customer(String u, String p) {
            super(u, p);
        }
        
        public int getPoints(){
            return points;
        }
        
        public Status getStatus(){
            return status;
        }
        
        public void setPoints(int p){
            this.points = p;
        }
        
        public void setStatus(Status s){
            //Storing state in strings is yanderedev-tier
            this.status = s;
        }
    }

    class Admin extends User {
        
        public Admin(String u, String p) {
            super(u, p);
        }
        
    } 
