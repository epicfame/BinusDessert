public class Yoghurt extends Dessert{
    private String probioticName;

    public Yoghurt(int id, String name, String type, int price, String probioticName) {
        super(id, name, type, price);
        this.probioticName = probioticName;
    }

    public String getProbioticName() {
        return this.probioticName;
    }

    public void setProbioticName(String probioticName) {
        this.probioticName = probioticName;
    }

    @Override
    public void print() {
        System.out.println("Yoghurt");
        System.out.println("Id: " + this.getId());
        System.out.println("Name: " + this.getName());
        System.out.println("Type: " + this.getType());
        System.out.println("Price: " + this.getPrice());
        System.out.println("Probiotic Name: " + this.getProbioticName());
        System.out.println();
    }
    
}
