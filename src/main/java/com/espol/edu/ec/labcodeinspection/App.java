package com.espol.edu.ec.labcodeinspection;
import java.util.*;

public final class App 
{
    private static Scanner scanner;

    private App(){
        
    }
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        // DEFINICION DE MENU
        Map<String, Double> menu = new HashMap<>();
        menu.put("MCFIESTA VEGGIE", 8.0);
        menu.put("PIZZA NAPOLETANA", 19.0);
        menu.put("ALFREDO PASTA", 14.0);
        menu.put("CEASAR SALAD", 10.0);

        // CREACION DE VARIABLES
        int totalQuantity = 0;
        double totalCost = 5.0; // Base cost is $5

        // SELECCION DE MENU
        System.out.println("Menu:");
        for (String item : menu.keySet()) {
            System.out.println(item + " - $" + menu.get(item));
        }

        Map<String, Integer> order = new HashMap<>();
        while (true) {
            System.out.print("Enter meal (or 'done' to finish): ");
            String meal = scanner.nextLine();
            if ("done".equals(meal)) {
                break;
            }
            if (!menu.containsKey(meal)) {
                System.out.println("Invalid meal selection. Please try again.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            // VALIDACION DE PRECIOS
            if (quantity <= 0 || quantity > 100) {
                System.out.println("Invalid quantity. Please enter a positive integer between 1 and 100.");
                continue;
            }

            // DISPONIBILIDAD DE MENU
            if (!menu.containsKey(meal)) {
                System.out.println("Invalid meal selection. Please try again.");
                continue;
            }

            // ACTUALIZACION DE MAP DE ORDENES
            order.put(meal, quantity);
            totalQuantity += quantity;
            totalCost += menu.get(meal) * quantity;
        }

        // CALCULO DE PRECIO
        if (totalQuantity > 5) {
            totalCost *= 0.9; // 10% 
        }
        if (totalQuantity > 10) {
            totalCost *= 0.8; // 20% 
        }

        // CASO ESPECIAL DE DESCUENTO
        if (totalCost > 100) {
            totalCost -= 25.0; //  $25 
        } else if (totalCost > 50) {
            totalCost -= 10.0; //  $10 
        }
        
        // CONFIRMACION DE USUARIO
        System.out.println("Selected Meals:");
        for (String meal : order.keySet()) {
            System.out.println(meal + " - Quantity: " + order.get(meal));
        }
        System.out.println("Total Cost: $" + totalCost);

        System.out.print("Confirm order? (yes/no): ");
        String confirmation = scanner.nextLine();

        if ("yes".equals(confirmation)) {
            // Step 8: Output
            System.out.println("Thank you for your order! Total cost: $" + (int) totalCost);
        } else {
            // User canceled the order
            totalCost = -1; // Return -1 if order is canceled
            System.out.println("Order canceled" + totalCost);         
        }
    }
}
