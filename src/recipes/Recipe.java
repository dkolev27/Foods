package recipes;

import product.Energy;
import product.Product;

import java.util.HashSet;

public class Recipe {

    // Fields
    private String name;
    private int maxPortions;
    private HashSet<Product> products;
    private StringBuilder instructions;
    private Energy energy;
    private double pricePerPortion;


    // Constructor
    final static int STARTING_CAPACITY = 10;

    public Recipe() {
        this.setProducts(STARTING_CAPACITY);
        this.setEnergy(new Energy());
        this.setInstructions(new StringBuilder(""));
    }


    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPortions() {
        return maxPortions;
    }

    public void setMaxPortions(int maxPortions) {
        this.maxPortions = maxPortions;
    }

    public HashSet<Product> getProducts() {
        return products;
    }

    public void setProducts(int capacity) {
        this.products = new HashSet<>(capacity);
    }

    public StringBuilder getInstructions() {
        return instructions;
    }

    public void setInstructions(StringBuilder instructions) {
        this.instructions = instructions;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public double getPricePerPortion() {
        return pricePerPortion;
    }

    public void setPricePerPortion(double pricePerPortion) {
        this.pricePerPortion = pricePerPortion;
    }


    // Utils
    public void addProduct(Product product) {
        this.products.add(product);

        this.energy.setCalories(this.energy.getCalories() + product.getEnergyValue().getCalories());
        this.energy.setFats(this.energy.getFats() + product.getEnergyValue().getFats());
        this.energy.setCarbohydrates(this.energy.getCarbohydrates() + product.getEnergyValue().getCarbohydrates());
        this.energy.setProteins(this.energy.getProteins() + product.getEnergyValue().getProteins());

        this.setEnergy(this.getEnergy());
    }

    public void printRecipe() {
        System.out.println("Recipe name: " + getName());
        System.out.println("Products: ");
        System.out.println("*************");
        for (Product product : getProducts()) {
            product.printProduct();
        }
        System.out.println();
        System.out.println("All energy: ");
        System.out.println("------------");
        this.getEnergy().printEnergy();
        System.out.println("------------");
        System.out.println();
        System.out.println("Instructions to prepare the meal:");
        System.out.println(getInstructions());
        System.out.println("Max portions: " + getMaxPortions());
        System.out.println("Price per portion: " + getPricePerPortion());
    }
}
