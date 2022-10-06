public class Icecream extends Dessert{

    private String creamName;

    public Icecream(int id, String name, String type, int price, String creamName) {
        super(id, name, type, price);
        this.creamName = creamName;
    }

    public String getCreamName() {
        return this.creamName;
    }

    public void setCreamName(String creamName) {
        this.creamName = creamName;
    }

    @Override
    public void print() {
        System.out.println("Icecream");
        System.out.println("Id: " + this.getId());
        System.out.println("Name: " + this.getName());
        System.out.println("Type: " + this.getType());
        System.out.println("Price: " + this.getPrice());
        System.out.println("Cream Name: " + this.getCreamName());
        System.out.println();
    }
    
}
