/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Data;

/**
 *
 * @author oliver
 */
public class Highscore {
    
    private String username;
    private int highscore;
    
    public Highscore(String username, int highscore){
        this.username = username;
        this.highscore = highscore;
    }
    
    public String getUsername(){
        return username;
    }
    
    public int getHighscore(){
        return highscore;
    }
    
}
