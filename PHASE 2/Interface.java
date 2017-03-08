import java.util.*;
import java.io.*;
/**
 * This is the interface that the user will use to process Inventory
 * 
 * @author  Marlon Fallorin / Matthew Baluyot
 * @ Due Date Febuary 24th 2017 - 11:59 pm
 */

public class Interface 
{

    public static void main(String args[])throws IOException
    {
        // define necessary variables here
        Scanner keyboard = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        String inventoryFile = "Inventory.txt";
        int number_of_items_in_warehouse = warehouse.loadData(inventoryFile);
        int number_of_items_info = warehouse.splitInfo(number_of_items_in_warehouse);
        //warehouse.loadSplitsArray(number_of_items_in_warehouse,number_of_items_info);
        //Will put each info given into individual arrays.
        warehouse.individualItemsForWarehouse(inventoryFile, number_of_items_in_warehouse,number_of_items_info);
        // place here the code for the processing requirements
        System.out.println("Please choose one of the following options: ");
        showMenu();
        int menuChoice = keyboard.nextInt();
        while(menuChoice != 9)
        {
            switch(menuChoice)
            {
                case 1: System.out.println("Inventory Item Inquiry");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 2: System.out.println("Order inventory items from Supplier");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 3: System.out.println("Receive shipment from Suppliers");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 4: System.out.println("Return items to Supplier");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 5: System.out.println("Ship items to Customers");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 6: System.out.println("Process Customer Order");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 7: System.out.println("Process Customer Returns");warehouse.validItemNumber(number_of_items_in_warehouse, number_of_items_info, menuChoice); break;
                case 8: System.out.println("End of Day Processing");warehouse.endOfDayProc(number_of_items_in_warehouse, number_of_items_info); break;
                default:
                System.out.println("Incorrect choice. Please try again");

            }
            System.out.println("Please choose one of the following options: ");
            showMenu();
            menuChoice = keyboard.nextInt();	
        }
        System.out.println ("Thank you for using the Inventory Processing System");
    }

    /**
     *  The Inventory processing menu
     */
    public static void showMenu()
    {
        System.out.println("\nMENU:");
        System.out.println("1) Inventory Item Inquiry");
        System.out.println("2) Order inventory items from Supplier");
        System.out.println("3) Receive shipment from Suppliers");
        System.out.println("4) Return items to Supplier");
        System.out.println("5) Ship items to Customers");
        System.out.println("6) Process Customer Order");
        System.out.println("7) Process Customer Returns");
        System.out.println("8) End of Day Processing");
        System.out.println();
        System.out.println("9) Exit");
    }
}
