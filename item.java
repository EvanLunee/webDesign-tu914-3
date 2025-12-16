public class item {
    private int id;
    private String title;
    private String description;
    private double startingPrice;
    private String owner;

    public item() {}
    public item(int id, String title, String description, double price, String owner){
        this.id = id;
        this.title = title;
        this.description = description;
        this.startingPrice = price;
        this.owner = owner;
    }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }
    
    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }
    
    public double getStartingPrice(){ return startingPrice; }
    public void setStartingPrice(double price){ this.startingPrice = price; }
    
    public String getOwner(){ return owner; }
    public void setOwner(String owner){ this.owner = owner; }
}
