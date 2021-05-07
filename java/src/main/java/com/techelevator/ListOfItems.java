package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfItems {

    private final List<Product> products = new ArrayList<>();

    //Vending Machine items read from input file when machine is started.
    public void readInItems(String file) {
        File inventoryFile = new File(file);
        try (Scanner fileScanner = new Scanner(inventoryFile)) {
            while (fileScanner.hasNext()) {
                String lineOfData = fileScanner.nextLine();
                String[] itemDetailsArray = lineOfData.split("\\|");
                String location = itemDetailsArray[0];
                String name = itemDetailsArray[1];
                BigDecimal price = new BigDecimal(itemDetailsArray[2]);
                String type = itemDetailsArray[3];
                if (type.equals("Chip")) {
                    Chip chip = new Chip(name, price, location);
                    addProducts(chip);
                } else if (type.equals("Candy")) {
                    Candy candy = new Candy(name, price, location);
                    addProducts(candy);
                } else if (type.equals("Drink")) {
                    Drink drink = new Drink(name, price, location);
                    addProducts(drink);
                } else if (type.equals("Gum")) {
                    Gum gum = new Gum(name, price, location);
                    addProducts(gum);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("SOLD OUT");
        }
    }

    // This method is useful for testing.
    public void addProducts(Product product) {
        products.add(product);
    }

    public Product[] arrayOfProducts() {
        Product[] productArray = new Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productArray[i] = products.get(i);
        }
        return productArray;
    }

    // This method is useful for testing.
    public List<Product> getProducts() {
        return products;
    }

    public void printAllProducts() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}
