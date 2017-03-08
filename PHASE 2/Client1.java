import java.util.Scanner;
import java.io.*;

/**
 * This is the interface that the user will use to process Inventory for Phase 2
 * 
 * @author  Your name here
 * @version 
 */
/*
 * Instructor: 
 * Assumptions: 
 * Known errors: None (or, a SPECIFIC explanation of what you know doesn't work)
 *                e.g. not "sometime bombs when reading file" bit "bombs when reading if > 20 lines")
 *
 * DELETE extraneous info from this comment
 */
public class Client1 
{
    /**
     *  Based on the user's choice, transactions are processed
     */
    public static void main(String args[]) throws IOException
    {
        // complete the main by adding the necessary variables and statements
        Scanner keyboard = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        String inventoryFile = "Inventory.txt";
        int number_of_items_in_warehouse = warehouse.loadData(inventoryFile);
        int number_of_items_info = warehouse.splitInfo(number_of_items_in_warehouse);
        warehouse.individualItemsForWarehouse(inventoryFile, number_of_items_in_warehouse,number_of_items_info);
        System.out.println("Please choose one of the following options: ");
        mainMenu();
        int menuChoice = keyboard.nextInt();
        while(menuChoice != 5)
        {
            switch(menuChoice)
            {
                case 1: System.out.println("Inventory item inquiry");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 2: System.out.println("Warehouse and Inventory Maintenance"); invMenuChoice(number_of_items_in_warehouse, number_of_items_info);break;
                case 3: System.out.println("Process transactions from the file"); break;
                case 4: System.out.println("End of Day Processing");warehouse.endOfDayProc(number_of_items_in_warehouse, number_of_items_info);break;
                default:
                System.out.println("Incorrect choice. Please try again");
            }
            System.out.println("Please choose one of the following options: ");
            mainMenu();
            menuChoice = keyboard.nextInt();
        }
        System.out.println ("Thank you for using the Inventory Processing System");
    }

    /**
     *  The Main menu
     */
    public static void mainMenu()
    {
        System.out.println("\nMAIN MENU:");
        System.out.println("1) Inventory item inquiry");
        System.out.println("2) Warehouse and Inventory Maintenance");
        System.out.println("3) Process transactions from the file");
        System.out.println("4) End of Day Processing");
        System.out.println();
        System.out.println("5) Exit");
    }

    public static void invMenuChoice(int items, int info)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please choose one of the following options: ");
        invMenu();
        int menuChoice = keyboard.nextInt();
        while(menuChoice != 4)
        {
            switch(menuChoice)
            {
                case 1: System.out.println("Adding an Item to the Warehoue"); break;
                case 2: System.out.println("Removing an Item from the Warehouse"); break;
                case 3: System.out.println("Changing the price of an Item in the Warehouse"); break;
                default:
                System.out.println("Incorrect choice. Please try again");
            }
            System.out.println("Please choose one of the following options: ");
            invMenu();
            menuChoice = keyboard.nextInt();
        }
    }

    /**
     *  The Inventory Maintenance menu
     */
    public static void invMenu()
    {
        System.out.println("\nINVENTORY PROCESSING MENU:");
        System.out.println("1) Adding an Item to the Warehoue");
        System.out.println("2) Removing an Item from the Warehouse");
        System.out.println("3) Changing the price of an Item in the Warehouse"); 
        System.out.println();
        System.out.println("4) Exit");
    } 
}
