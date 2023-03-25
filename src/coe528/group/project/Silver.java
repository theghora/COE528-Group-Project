/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

/**
 *
 * @author tahag
 */
class Silver extends State{
     protected void setGold(Customer C){
        if (C.getPoints()>=1000){
            //cast state class to gold class
        }
    }
    protected void setSilver(Customer C){
         if (C.getPoints()<1000){
            //cast state class to silver class
        }
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
    
}


