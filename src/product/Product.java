package product;

public class Product {

    // Fields
    private String name;
    private Energy energyValue;
    private double price;
    private double quantity;


    // Constructor
    public Product(String name, Energy energyValue, double price, double quantity) {
        this.setName(name);
        this.setEnergyValue(energyValue);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

//    public Product(String name) {
//        setName(name);
//    }

    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Energy getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(Energy energyValue) {
        this.energyValue = energyValue;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    // Utils
    public void printProduct() {
        System.out.println();
        System.out.println("Product name: " + getName());
        System.out.println("Energy value:");
        System.out.println("--------------");
        getEnergyValue().printEnergy();
        System.out.println("--------------");
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getQuantity());
    }
}
