import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class userAction extends ActionSupport implements SessionAware {
    private String username;
    private String password;
    private String email;
    private int userId;
    private ArrayList<user> usersList;
    private Map<String,Object> session;
    private user user; 
    private user otherUser;    
    private int viewUserId;

    private Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
    }

    public String register(){
        try(Connection con = getConnection()){
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username,password,email) VALUES(?,?,?)"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
            return SUCCESS;
        } catch(Exception e){ e.printStackTrace(); return INPUT; }
    }

    public String login(){
        try(Connection con = getConnection()){
            PreparedStatement ps = con.prepareStatement(
                "SELECT user_id,username FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                session.put("username", rs.getString("username"));
                session.put("userId", rs.getInt("user_id"));
                return SUCCESS;
            } else return ERROR;
        } catch(Exception e){ e.printStackTrace(); return ERROR; }
    }

    public String logout(){
        session.clear();
        return SUCCESS;
    }

    public String viewProfile() {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT user_id, username, email FROM users WHERE user_id=?"
            );
            ps.setInt(1, (int) session.get("userId"));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new user(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    null,
                    rs.getString("email")
                );
            }
        } catch(Exception e){ e.printStackTrace(); }
        return SUCCESS;
    }

    // View another user by ID
    public String viewOtherProfile() {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT user_id, username, email FROM users WHERE user_id=?"
            );
            ps.setInt(1, viewUserId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                otherUser = new user(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    null,
                    rs.getString("email")
                );
            }
        } catch(Exception e){ e.printStackTrace(); }
        return SUCCESS;
    }

    public String viewAllUsers() {
        usersList = new ArrayList<>();
        try (Connection con = getConnection()) {
            ResultSet rs = con.createStatement().executeQuery(
                "SELECT user_id, username, email FROM users"
            );
            while (rs.next()) {
                usersList.add(new user(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    null,
                    rs.getString("email")
                ));
            }
        } catch (Exception e){ e.printStackTrace(); }
        return SUCCESS;
    }
    
    public ArrayList<user> getUsersList() { return usersList; }
    public user getUser() { return user; }
    public user getOtherUser() { return otherUser; }
    public void setViewUserId(int id){ viewUserId = id; }
    public void setSession(Map<String,Object> s){ session = s; }
}