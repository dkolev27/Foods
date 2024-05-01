package manager;

import product.Energy;
import product.Product;
import recipes.Recipe;
import recipes.Recipes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Manager {
    public static void manage(Scanner scanner) {

        Recipes allRecipes = readRecipesFromFile("src\\recipes\\recipes.txt");
        String[] products = null;

        String command;
        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("print all meals")) {
                allRecipes.printAllRecipes();
            } else if (command.equalsIgnoreCase("check products")) {
                System.out.print("Input products: ");
                // TODO Извличаме продуктите от стринг в Product обект
                products = scanner.nextLine().split(", |,");
                printAllPossibleMeals(products, allRecipes);
            } else if (command.equalsIgnoreCase("choose meal")) {
                if (products == null) {
                    System.out.println("First, you have to input check products. Try again!");
                    continue;
                }
                //printExactMeal(scanner, allRecipes, products);
                TESTprintExactMealTEST(scanner, allRecipes, products);
                // TODO What more products do we need
            } else if (command.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }


    // Util methods
    private static void printExactMeal(Scanner scanner, Recipes allRecipes, String[] products) {
        System.out.print("Tell me which meal you want to see: ");
        String meal = scanner.nextLine();

        for (Recipe recipe : allRecipes.getRecipes()) {
            if (recipe.getName().equals(meal)) {
                recipe.printRecipe();

                System.out.println("\nYou also need these products: ");
                for (Product product : recipe.getProducts()) {

                    for (int i = 0; i < products.length; i++) {
                        String[] prodArgs = products[i].split("-");
                        String prodName = prodArgs[1];

                        /*if (!product.getName().equals(prodName)) {
                            System.out.println(product.getQuantity() + "-" + product.getName());
                        }*/
                    }
                }
            }
        }
    }

    // TODO String[] products да бъде HashSet<Product> availableProducts
    private static void TESTprintExactMealTEST(Scanner scanner, Recipes allRecipes, String[] products) {
        System.out.print("Tell me which meal you want to see: ");
        String meal = scanner.nextLine();

        // Extract entered products outside of this function
        HashSet<String> availableProducts = new HashSet<>();
        for (int i = 0; i < products.length; i++) {
            String[] prodArgs = products[i].split("-");
//            Product currPr = new Product(prodArgs[1]);
            availableProducts.add(prodArgs[1]);
        }

        for (Recipe recipe : allRecipes.getRecipes()) {
            if (recipe.getName().equals(meal)) {
                recipe.printRecipe();


                System.out.println("\nYou also need these products: ");
                for (Product product : recipe.getProducts()) {

                    // TODO Да се сравняват продуктите по име - то е уникално
                    if (!availableProducts.contains(product.getName())) {
                        System.out.println(product.getQuantity() + "-" + product.getName());
                    }
                }
            }
        }
    }

    private static void printAllPossibleMeals(String[] products, Recipes allRecipes) {
        Boolean[] hasProducts = new Boolean[products.length];

        String productName;
        double quantity;
        for (Recipe recipe : allRecipes.getRecipes()) {

            int cnt = 0;
            for (String product : products) {

                String[] prod = product.split("-");
                quantity = Double.parseDouble(prod[0]);
                productName = prod[1];

                for (Product productFromRecipe : recipe.getProducts()) {

                    String prodFromRecipeName = productFromRecipe.getName();

                    if (productName.equals(prodFromRecipeName) && quantity >= productFromRecipe.getQuantity()) {
                        // Mark as available
                        hasProducts[cnt++] = true;
                        break;
                    }
                }

            }

            int i = 0;
            for (; i < hasProducts.length; i++) {
                if (hasProducts[i] == null) {
                    i--;
                    break;
                }
            }

            if (i == hasProducts.length) {
                System.out.println(recipe.getName());

                for (int k = 0; k < hasProducts.length; k++) {
                    hasProducts[k] = null;
                }
            }
        }
    }


    public static Recipes readRecipesFromFile(String filepath) {
        Recipes allRecipes = new Recipes();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Recipe recipe = new Recipe();

                while (true) {

                    if (line.equals("RN:")) {
                        recipe.setName(reader.readLine());
                        line = reader.readLine();
                        line = reader.readLine();
                    }

                    switch (line) {
                        case "Products:" -> {
                            while (!(line = reader.readLine()).equals("<endProducts>")) {
                                String[] currentLine = line.split("-");
                                double quantity = Double.parseDouble(currentLine[0]);
                                String prodName = currentLine[1];
                                double proteins = Double.parseDouble(currentLine[2]);
                                double fats = Double.parseDouble(currentLine[3]);
                                double carbohydrates = Double.parseDouble(currentLine[4]);
                                double calories = Double.parseDouble(currentLine[5]);
                                double price = Double.parseDouble(currentLine[6]);

                                Energy energy = new Energy(proteins, fats, carbohydrates, calories);
                                Product product = new Product(prodName, energy, price, quantity);

                                recipe.addProduct(product);
                            }
                            line = reader.readLine();
                        }
                        case "Instructions:" -> {
                            while (!(line = reader.readLine()).equals("<endInstructions>")) {
                                recipe.getInstructions().append(line).append('\n');
                            }
                            line = reader.readLine();

                        }
                        case "Max portions:" -> {
                            recipe.setMaxPortions(Integer.parseInt(reader.readLine()));

                            line = reader.readLine();
                        }
                        case "Price per portion:" -> {
                            recipe.setPricePerPortion(Double.parseDouble(reader.readLine()));
                            allRecipes.addRecipe(recipe);

                            line = reader.readLine();
                        }
                    }


                    if (line.equals("<endRecipe>")) {
                        break;
                    }
                    line = reader.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allRecipes;
    }
}
