package product;

public class Energy {

    // Fields
    private double proteins;
    private double fats;
    private double carbohydrates;
    private double calories;


    // Constructors
    public Energy() {
        this.setProteins(0.0);
        this.setFats(0.0);
        this.setCarbohydrates(0.0);
        this.setCalories(0.0);
    }

    public Energy(double proteins, double fats, double carbohydrates, double calories) {
        this.setProteins(proteins);
        this.setFats(fats);
        this.setCarbohydrates(carbohydrates);
        this.setCalories(calories);
    }


    // Setters and Getters
    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }


    // Utils
    public void printEnergy() {
        System.out.println("Calories: " + getCalories());
        System.out.println("Fats: " + getFats());
        System.out.println("Carbohydrates: " + getCarbohydrates());
        System.out.println("Proteins: " + getProteins());
    }
}
