package coe528.group.project;

class User {
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
        
        protected State Status;
        
//        enum Status {
//            SILVER,
//            GOLD
//        }
        
        public Customer(String u, String p, int points) {
            super(u, p);
            this.points=points;
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
            //Storing state in strings is yanderedev-tier
            this.Status=s;
        }
        
        public void buy(double totalCost){
            
        }
        
        public double redeemPointsBuy(double totalcost){
            return points;
        }
        
        protected void setGold(){
            Status.setGold(this);
        }
        
        protected void setSilver(){
            Status.setSilver(this);
        }
    }

