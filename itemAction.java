import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class itemAction extends ActionSupport implements SessionAware {
    private String title;
    private String description;
    private double startingPrice;
    private ArrayList<item> itemsList;
    private Map<String,Object> session;

    private Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
    }

    public String addItem(){
        try(Connection con = getConnection()){
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO items(user_id,title,description,starting_price) VALUES(?,?,?,?)"
            );
            ps.setInt(1, (int)session.get("userId"));
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setDouble(4, startingPrice);
            ps.executeUpdate();
            return SUCCESS;
        } catch(Exception e){ e.printStackTrace(); return INPUT; }
    }

    public String viewItems(){
        itemsList = new ArrayList<>();
        try(Connection con = getConnection()){
            ResultSet rs = con.createStatement().executeQuery(
                "SELECT i.item_id, i.title, i.description, i.starting_price, u.username " +
                "FROM items i JOIN users u ON i.user_id=u.user_id"
            );
            while(rs.next()){
                itemsList.add(new item(
                    rs.getInt("item_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDouble("starting_price"),
                    rs.getString("username")
                ));
            }
        } catch(Exception e){ e.printStackTrace(); }
        return SUCCESS;
    }

    public ArrayList<item> getItemsList(){ return itemsList; }
    public void setTitle(String t){ title = t; }
    public void setDescription(String d){ description = d; }
    public void setStartingPrice(double p){ startingPrice = p; }
    public void setSession(Map<String,Object> s){ session = s; }
}
