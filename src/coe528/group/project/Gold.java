
package coe528.group.project;

/**
 *
 * @author tahag
 */
class Gold extends State{    
    @Override
    protected void setSilver(Customer C) {
        if(C.getPoints()< 1000){
            C.setStatus(new Silver());
         }else{
            setGold(C);
        }
    }
    @Override
    protected void setGold(Customer C) {
        if(C.getPoints()>= 1000){
             C.setStatus(new Gold());
         }else{
            setSilver(C);
        }
    }

    @Override
    public String toString() {
        return "Gold"; 
    }
    
}