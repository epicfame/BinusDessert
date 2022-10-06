public class Transaction {
    private String id;
    private String userName;
    private String dessertName;
    private int quantity;
    private int price;

    public Transaction(String id, String userName, String dessertName, int quantity, int price) {
        this.id = id;
        this.userName = userName;
        this.dessertName = dessertName;
        this.quantity = quantity;
        this.price = price;
    }

    public void print(){
        System.out.println("Transaction");
        System.out.println("Id: " + this.id);
        System.out.println("User Name: " + this.userName);
        System.out.println("Dessert Name: " + this.dessertName);
        System.out.println("Quantity: " + this.quantity);
        System.out.println("Price: " + this.price);
        System.out.println();
    }


    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDessertName() {
        return this.dessertName;
    }

    public void setDessertName(String dessertName) {
        this.dessertName = dessertName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
