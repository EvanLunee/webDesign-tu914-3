import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class bidAction extends ActionSupport implements SessionAware {
    private int itemId;
    private double bidAmount;
    private ArrayList<bid> bidsList;
    private Map<String,Object> session;

    private Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
    }

    public String makeBid(){
        try(Connection con = getConnection()){
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO bids(item_id,user_id,bid_amount) VALUES(?,?,?)"
            );
            ps.setInt(1, itemId);
            ps.setInt(2, (int)session.get("userId"));
            ps.setDouble(3, bidAmount);
            ps.executeUpdate();
            return SUCCESS;
        } catch(Exception e){ e.printStackTrace(); return INPUT; }
    }

    public String viewMyBids(){
        bidsList = new ArrayList<>();
        try(Connection con = getConnection()){
            PreparedStatement ps = con.prepareStatement(
                "SELECT b.bid_id, b.bid_amount, i.title " +
                "FROM bids b JOIN items i ON b.item_id=i.item_id " +
                "WHERE b.user_id=?"
            );
            ps.setInt(1, (int)session.get("userId"));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                bidsList.add(new bid(
                    rs.getInt("bid_id"),
                    0,
                    rs.getString("title"),
                    rs.getDouble("bid_amount")
                ));
            }
        } catch(Exception e){ e.printStackTrace(); }
        return SUCCESS;
    }

    public ArrayList<bid> getBidsList(){ return bidsList; }
    
    public void setItemId(int id){ itemId = id; }
    
    public void setBidAmount(double amount){ bidAmount = amount; }
    
    public void setSession(Map<String,Object> s){ session = s; }
}
