public class bid {
    private int id;
    private int itemId;
    private String bidder;
    private double amount;

    public bid() {}
    public bid(int id, int itemId, String bidder, double amount){
        this.id = id;
        this.itemId = itemId;
        this.bidder = bidder;
        this.amount = amount;
    }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    
    public int getItemId(){ return itemId; }
    public void setItemId(int itemId){ this.itemId = itemId; }
    
    public String getBidder(){ return bidder; }
    public void setBidder(String bidder){ this.bidder = bidder; }
    
    public double getAmount(){ return amount; }
    public void setAmount(double amount){ this.amount = amount; }
}
