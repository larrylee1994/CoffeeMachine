package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static Scanner input = new Scanner(System.in);
    static Machine machine = new Machine();

    public static void main(String[] args) {

        String action = "";
        while(!action.equals("exit")) {
            action = RunMachine();
        }
    }

    private static String RunMachine() {
        String action;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        action = input.next();
        switch (action) {
            case "buy":
                System.out.println(
                        "What do you want to buy? " +
                        "1 - espresso, 2 - latte, 3 - cappuccino, " +
                        "back - to main menu:");
                machine.buy(input.next());
                break;
            case "fill":
                getFillAmounts();
                break;
            case "take":
                machine.take();
                break;
            case "remaining":
                System.out.println(machine);
                break;
            case "exit":
                break;
            default:
                System.out.println("That is not a valid choice!");
                break;
        }
        return action;
    }

    private static void getFillAmounts() {
        System.out.println("Write how many ml of water do you want to add:");
        int water = input.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int milk = input.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int beans = input.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cups = input.nextInt();
        machine.fill(water, milk, beans, cups);
    }
}

class Machine {

    final String ESPRESSO = "1";
    final String LATTE = "2";
    final String CAPPUCCINO = "3";
    final String BACK = "back";

    private int waterAvailable = 400;
    private int milkAvailable = 540;
    private int beansAvailable = 120;
    private int cupsAvailable = 9;
    private int moneyAvailable = 550;

    public void buy (String choice) {

        switch (choice) {
            case ESPRESSO:
                buyCoffee(-250,0,-16,4);
                break;
            case LATTE:
                buyCoffee(-350, -75, -20, 7);
                break;
            case CAPPUCCINO:
                buyCoffee(-200, -100, -12, 6);
                break;
            case BACK:
                break;
            default:
                System.out.println("That is not a valid choice!");
                break;
        }
    }

    public void fill (int water, int milk, int beans, int cups) {
        waterAvailable += water;
        milkAvailable += milk;
        beansAvailable += beans;
        cupsAvailable += cups;
    }


    public void take () {
        System.out.println("I gave you $" + moneyAvailable);
        moneyAvailable -= moneyAvailable;
    }

    private void buyCoffee(int water, int milk, int beans, int money) {
        boolean enoughSupplies = checkSupplies(Math.abs(water), Math.abs(milk), Math.abs(beans));
        if(enoughSupplies){
            System.out.println("I have enough resources, making you a coffee!");
            waterAvailable += water;
            milkAvailable += milk;
            beansAvailable += beans;
            moneyAvailable += money;
            cupsAvailable--;
        }
    }

    private boolean checkSupplies(int waterRequired, int milkRequired, int beansRequired) {
        boolean result = true;
        if (waterAvailable < waterRequired) {
            System.out.println("Sorry not enough water!");
            result = false;
        }
        if (milkAvailable < milkRequired) {
            System.out.println("Sorry not enough milk!");
            result = false;
        }
        if (beansAvailable < beansRequired) {
            System.out.println("Sorry not enough beans!");
            result = false;
        }
        if (cupsAvailable < 1) {
            System.out.println("Sorry not enough cups!");
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        return "The coffee machine has:\n" +
                waterAvailable + " of water\n"+
                milkAvailable + " of milk\n" +
                beansAvailable + " of coffee beans\n" +
                cupsAvailable + " of disposable cups\n" +
                moneyAvailable + " of money\n";
    }
}
