package coe528.group.project;

public class User {
    private String username;
    private String password;
    
    public User(String u, String p){
        this.username = u;
        this.password = p;
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
        
        protected State Status;
        
        
        public Customer(String u, String p, int points) {
            super(u, p);
            this.points=points;
            if(points <1000){
                this.Status=new Silver();
            }else{
                this.Status=new Gold();
            }
        }
        
        public int getPoints(){
            return points;
        }
        
        public State getStatus(){
            return Status;
        }
        
        public void setPoints(int p){
            this.points = p;
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
    }

