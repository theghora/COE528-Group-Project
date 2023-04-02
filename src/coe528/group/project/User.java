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

