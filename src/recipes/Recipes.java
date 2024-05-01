package recipes;

import java.util.LinkedList;
import java.util.List;

public class Recipes {

    // Field
    private List<Recipe> recipes;


    // Constructor
    public Recipes() { // Every time when the constructor is called, it will get the recipes from the file
        this.setRecipes();
    }


    // Setter and Getter
    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes() {
        this.recipes = new LinkedList<>();

        // To read from file
    }


    // Other methods
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe() {
        // TODO
    }

    public void searchForRecipe() {
        // TODO
    }

    public void printAllRecipes() {
        for (Recipe recipe : getRecipes()) {
            recipe.printRecipe();
            System.out.println("=====================");
        }
    }
}
