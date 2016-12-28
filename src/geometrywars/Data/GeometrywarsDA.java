/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Data;

import geometrywars.GameObjects.Player.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oliver
 */
public class GeometrywarsDA {
    public final static GeometrywarsDA instance = new GeometrywarsDA();

    private static final String URL = "jdbc:mysql:YourDatabase";
    private static final String USER = "Your username";
    private static final String PASSWORD = "Your password";

    private Connection connection;

    protected GeometrywarsDA(){
        this.registerDriver();
        this.openConnection();
    }

    private void registerDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new GeometryExceptions("Unable to load MySQL driver.", ex);
        }
    }

    private void openConnection(){
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new GeometryExceptions("Unable to open connection.", ex);
        }
    }
    
    public static GeometrywarsDA getInstance(){
        return instance;
    }
    
    public Player getPlayer(int locX, int locY, String name){
        try {
            String sql = "SELECT * FROM users WHERE username='"+ name +"';";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            Player p = null;
            ResultSet rs = prep.executeQuery();

            rs.next();
            
            p = new Player(locX, locY, rs.getInt("playerId"), name);//FIXME
            p.setHighscore(rs.getInt("highscore"));
            p.setCurrentLevel(rs.getInt("current_level"));
            p.setCredits(rs.getInt("GameCredits"));
            rs.close();
            prep.close();
            return p;

        } catch (SQLException ex) {
            throw new GeometryExceptions("Unable to retrieve user.", ex);
        }
    }
    
    public void updateLevel(int id, int currentLevel){
        try {
            String sql = "UPDATE users SET current_level=" + currentLevel + " WHERE playerId=" + id + ";";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new GeometryExceptions("Unable to update current level.", ex);
        } 
    }
    
    public void updateHighscore(int id, int highscore){
        try {
            String sql = "UPDATE users SET highscore=" + highscore + " WHERE playerId=" + id + ";";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.executeUpdate();


        } catch (SQLException ex) {
            throw new GeometryExceptions("Unable to update current highscore.", ex);
        } 
    }
    
    public void addPlayer(String username){
        try {
            String sql = "INSERT INTO users (username) VALUES(?);";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, username);

            prep.execute();


        } catch (SQLException ex) {
            throw new GeometryExceptions("Unable to add player.", ex);
        } 
    }
    
    public void registerUser(String username, String password, String email){
        try {
            String sql	= "insert into users(username, password, email) values(?, ?, ?)";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);
            prep.setString(3, email);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            throw new GeometryExceptions("Unable to add user.", ex);
        }
    }
    
    public String getPassword(String username)
        {
       String resultPw = null;
        try
        {
            String sql = "select password from users where username like ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, username);

            ResultSet rs = prep.executeQuery();
            
            if (rs.next()){
            resultPw = rs.getString("password");            
            }
            
            rs.close();
            prep.close();

            return resultPw;
        }
        catch (SQLException ex)
        {
            throw new GeometryExceptions("Unable to retrieve userData.", ex);
        }
    }
    
    public List<Highscore> getHighscores(){
        try {
            String sql = "SELECT username, highscore FROM users ORDER BY highscore DESC;";
            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            List<Highscore> highscoreList = new ArrayList<>();
            
            while (rs.next()) {
                highscoreList.add(new Highscore(rs.getString("username"), rs.getInt("highscore")));
            }
            
            return highscoreList;
            
        } catch (SQLException ex) {
            throw new GeometryExceptions("Unable to get highscores.", ex);
        }
    }
}
