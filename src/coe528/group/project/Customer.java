/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import javafx.scene.control.CheckBox;

/**
 *
 * @author super
 */
    public class Customer extends User {
        
        protected int points;
        
        protected State Status;
        
        private CheckBox selected;
        
        public Customer(String u, String p, int points) {
            super(u, p);
            this.points=points;
            if(points <1000){
                this.Status=new Silver();
            }else{
                this.Status=new Gold();
            }
            this.selected = new CheckBox();
        }
        
        public int getPoints(){
            if(points <1000){
                this.Status=new Silver();
            }else{
                this.Status=new Gold();
            }
            return points;
        }
        
        public State getStatus(){
            if(points <1000){
                this.Status=new Silver();
            }else{
                this.Status=new Gold();
            }
            return Status;
        }
        
        public void setPoints(int p){       
            this.points = p;
            if(points <1000){
                this.Status=new Silver();
            }else{
                this.Status=new Gold();
            }
        }
        
        public void setStatus(State s){
            this.Status=s;
        }
        
        public void buy(double totalCost){
            points += (int)(totalCost * 10);
            if(points <1000){
                setGold();
            }else{
                setSilver();
            }
        }
        
        public double redeemPointsBuy(double totalcost){
            double redeemed = points/100;
            points =0;
            
            totalcost -=redeemed;
            if(totalcost < 0) {
                points = (int)(-totalcost)*100;
                totalcost = 0.0;
            }
            points = points + (int)(totalcost * 10);

            if(points < 1000){
                setSilver();}
            else{
                setGold();}

            return totalcost;
        }
        
        protected void setGold(){
            Status.setGold(this);
        }
        
        protected void setSilver(){
            Status.setSilver(this);
        }

        public CheckBox getSelected() {
            return selected;
        }
        public void setSelected(CheckBox selected) {
            this.selected = selected;
        }
        
        public String toString(){
            return points+","+this.getUsername()+","+this.getPassword()+"\n";
        }
    }
