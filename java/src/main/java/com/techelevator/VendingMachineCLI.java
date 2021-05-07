package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String[] PURCHASE_MENU_OPTIONS = {"Feed Money", "Select Product", "Finish Transaction"};
    private static final String[] MONEY_OPTIONS = {"$1", "$2", "$5", "$10", "Back"};
    private static final String AUDIT_LOG_FILE = "Log.txt";
    private static final String INPUT_FILE = "vendingmachine.csv";
    private final Menu menu;
    private final Funds totalFunds = new Funds();
    private static final ListOfItems displayList = new ListOfItems();
    private Product[] VENDING_OPTIONS;
    private final AuditLog auditLog = new AuditLog();

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                displayList.printAllProducts();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                System.out.println("Current Money Provided: $" + totalFunds.getFunds().setScale(2, RoundingMode.UNNECESSARY));
                handlePurchaseOptions();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        displayList.readInItems(INPUT_FILE);
        cli.run();
    }

    public void handlePurchaseOptions() {
        boolean stay = true;

        while (stay) {
            String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
            if (choice.equals("Feed Money")) {
                handleFeedMoney();
            } else if (choice.equals("Select Product")) {
                VENDING_OPTIONS = displayList.arrayOfProducts();
                menuChoices();
            } else if (choice.equals("Finish Transaction")) {
                System.out.println(totalFunds.change());
                auditLog.addAuditEntry("GIVE CHANGE:", totalFunds.getFunds(), new BigDecimal("0"), AUDIT_LOG_FILE);
                totalFunds.decreaseFunds(totalFunds.getFunds());
                stay = false;
            }
            System.out.println("Current Money Provided: $" + totalFunds.getFunds().setScale(2, RoundingMode.UNNECESSARY));
        }
    }

    public void handleFeedMoney() {
        boolean stay = true;
        while (stay) {
            String choice = (String) menu.getChoiceFromOptions(MONEY_OPTIONS);
            if (choice.equals("$1")) {
                auditLog.addAuditEntry("FEED MONEY:", totalFunds.getFunds(), totalFunds.getFunds().add(new BigDecimal("1")), AUDIT_LOG_FILE);
                totalFunds.addFunds(new BigDecimal("1"));
                System.out.println("Feeding $1...");
            } else if (choice.equals("$2")) {
                auditLog.addAuditEntry("FEED MONEY:", totalFunds.getFunds(), totalFunds.getFunds().add(new BigDecimal("2")), AUDIT_LOG_FILE);
                totalFunds.addFunds(new BigDecimal("2"));
                System.out.println("Feeding $2...");
            } else if (choice.equals("$5")) {
                auditLog.addAuditEntry("FEED MONEY:", totalFunds.getFunds(), totalFunds.getFunds().add(new BigDecimal("5")), AUDIT_LOG_FILE);
                totalFunds.addFunds(new BigDecimal("5"));
                System.out.println("Feeding $5...");
            } else if (choice.equals("$10")) {
                auditLog.addAuditEntry("FEED MONEY:", totalFunds.getFunds(), totalFunds.getFunds().add(new BigDecimal("10")), AUDIT_LOG_FILE);
                totalFunds.addFunds(new BigDecimal("10"));
                System.out.println("Feeding $10...");
            } else if (choice.equals("Back")) {
                stay = false;
            }
        }
    }

    public void menuChoices() {
        boolean newStay = true;
        while (newStay) {
            Product itemChoice = (Product) menu.getChoiceFromOptions(VENDING_OPTIONS);
            if (itemChoice.getQuantity() == 0) {
                System.out.println("SOLD OUT");
                newStay = false;
            } else if (totalFunds.getFunds().compareTo(itemChoice.getPrice()) < 0) {
                System.out.println("Not enough funds for item.");
                newStay = false;
            } else {
                String productLocation = itemChoice.getName() + " " + itemChoice.getLocation();
                auditLog.addAuditEntry(productLocation, totalFunds.getFunds(), totalFunds.getFunds().subtract(itemChoice.getPrice()), AUDIT_LOG_FILE);
                totalFunds.decreaseFunds(itemChoice.getPrice());
                BigDecimal endingFunds = totalFunds.getFunds();
                System.out.println("Here is your choice: " + "Name: " + itemChoice.getName() + " Price: " + itemChoice.getPrice() + " Money Remaining: " + endingFunds);
                itemChoice.decreasedQuantity();
                System.out.println(itemChoice.getMessage());
                newStay = false;
            }
        }
    }
}