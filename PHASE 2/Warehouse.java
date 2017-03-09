import java.util.*;
import java.io.*;
/**
 * Warehouse contains the different items in stock
 * 
 * @author {Marlon Fallorin / Matthew Baluyot}
 * 
 * Additonal Information / Problems:
 *                                  
 *
 */

public class Warehouse
{
    // instance variables (fields)
    Scanner keyboard = new Scanner(System.in);
    String warehouse_Items[];
    String info_Array[];
    Item item = new Item();

    //Length of List and Info
    int length_Of_List = 0;
    int length_Of_Info = 0;

    private final static int MAX = 60;
    private ArrayList<Item> item_List = new ArrayList<Item>();

    String itemNumber;
    String itemInfo;
    String itemNameInfo;
    int onHandInfo;
    int committedInfo;
    int onOrderInfo;
    double unitPriceInfo;
    int reorderInfo;
    int econOrderQtyInfo;

    // the constructor
    public Warehouse() 
    {
        warehouse_Items = new String [MAX];
    }

    //Will load the data
    public int loadData(String file)throws IOException
    {
        Scanner file_Inventory = new Scanner (new File(file));
        String inventoryItems;
        int number = 0;
        while(file_Inventory.hasNextLine())
        {
            warehouse_Items[number] = file_Inventory.nextLine();
            if(file_Inventory.hasNextLine())
            {
                number++;
            }
        }
        file_Inventory.close();
        length_Of_List = number + 1;
        return length_Of_List;
    }

    //Will split the the inventory into separate arrays.
    public int splitInfo(int itemNumber)
    {
        String info;
        for(int i = 0; i < itemNumber; i++)
        {
            info_Array = warehouse_Items[i].split(" ");
        }
        length_Of_Info = info_Array.length;
        return length_Of_Info;
    }

    //This allows that if the text files add an additonal info. All I need to do is change this and it will be updated.
    public void individualItemsForWarehouse(String file, int numberOfItems, int numberOfIndividualInfo)throws IOException
    {
        Scanner fileInput = new Scanner(new File(file));
        while(fileInput.hasNextLine())
        {
            item_List.add(new Item(fileInput.next(),
                    fileInput.next(),
                    fileInput.nextInt(),
                    fileInput.nextInt(),
                    fileInput.nextInt(),
                    fileInput.nextDouble(),
                    fileInput.nextInt(),
                    fileInput.nextInt()));
        }
        fileInput.close();
    }

    //This class will check if the item number is valid.
    public String validItemNumber(int items, int info)
    {
        Scanner keyboard = new Scanner(System.in);
        boolean valid = false;
        System.out.println("Please enter a valid item number: ");
        String itemNumber = keyboard.next();
        String infoID = "";
        for(int i = 0; i < items; i++)
        {
            infoID =  item_List.get(i).getItemNo();
            if(itemNumber.equals(infoID))
            {
                System.out.println("Valid Item Number: " + infoID);

                valid = true;
            }         
        }
        System.out.println();
        if(valid == false)
        {
            System.out.println("Invalid Item Number");
        }

        if(valid == true)
        {
            System.out.println("Valid Item Number:" + infoID);
        }
        return infoID;
    }

    //This will set all the items that passed through the valididation.
    public Item validateAllItems(String itemNumber, int items)
    {
        String infoID = "";
        Item foundItem = null;
        for(int i = 0; i < items; i++)
        {
            infoID = item_List.get(i).getItemNo();
            if(itemNumber.equals(infoID))
            { 
                foundItem = item_List.get(i);
            }
        }
        return foundItem;
    }

    //Will check to see if the inventory is available for customers.
    public void inventoryInformationInquiry(int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        if (foundItem == null)
        {
            System.out.println("Item: " + itemNumber + " is not stocked");
        }
        else
        {
            foundItem.ItemDetails();
        }
    }

    //Will order items for the customer.
    public void orderInventoryItems (int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        System.out.print("Enter the amount of "+ foundItem.getItemName() + " to be ordered: ");
        int ordered = keyboard.nextInt();
        //When ordering items. The calculations should be in the item class. So the user won't be able to change it. 
        if (foundItem != null)
        {
            System.out.println("Item number: " + foundItem.getItemNo() + " has been ordered");
            foundItem.orderItems(ordered);
            //Check to see if there has been any changes
            System.out.println("The amount ordered is: " + ordered + " to " + itemNumber);
            System.out.println("On order amount is: " + foundItem.getOnOrder());
        }
        else
        {
            System.out.println("Item: " + itemNumber + ". Is more than the item on hand.\n" + "Current On hand is: " + foundItem.getOnHand());
        }
    }

    //Will be receiving shipments from the supplier.
    public void receiveShipmentFromSuppliers(int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        System.out.print("Enter the amount of shipments received from the suppliers: ");
        int received = keyboard.nextInt();
        foundItem.receiveShipment(received);
        System.out.println("Received amount of: " + received + " of the item " + itemNumber);
        System.out.println("On order amount is: " + foundItem.getOnOrder());
        System.out.println("On hand amount is: " + foundItem.getOnHand());
    }

    //Returning to items to the supplers. Meaning that customers did not want the item(s) anymore.
    public void returnItemsToSupplier(int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        System.out.println("Enter the amount that will be returned to the supplier: ");
        int returned = keyboard.nextInt();
        foundItem.returningItems(returned);
        System.out.println("Returned amount of: " + returned + " of the item " + itemNumber);
        System.out.println("On hand amount is: " + foundItem.getOnHand());
    }

    //Shipping the items to the customers.
    public void shipItemsToCustomers(int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        System.out.println("Enter the amount that is requested to be shipped to the customer: ");
        int requested = keyboard.nextInt();
        foundItem.shippingTheItemsToCustomers(requested);
        System.out.println("Shipped amount of: " + requested + " of item " + foundItem.getItemNo());
        System.out.println("Committed is: " + foundItem.getCommitted());
        System.out.println("On hand amount is: " + foundItem.getOnHand());
    }

    //Processing Customer orders.
    public void processCustOrder(int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        System.out.println("Enter in the amount ordered by the customer");
        int numUnit = keyboard.nextInt();
        foundItem.customerOrder(numUnit);
        System.out.println("The customer has ordered: " + numUnit + " of Items. " + itemNumber + "  " + foundItem.getItemName());
        System.out.println("Committed is: " + foundItem.getCommitted());
        System.out.println("On Hand amount is: " + foundItem.getOnHand());
    }

    //Processing Customer Returns.
    public void processCustReturn(int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        System.out.println("Input the amount returned: ");
        int returned = keyboard.nextInt();
        foundItem.customerReturns(returned);
        System.out.println("The Customer is returning: " + returned + " of items." + foundItem.getItemNo() + " " + foundItem.getItemName());
        System.out.println("On hand amount is: " + foundItem.getOnHand());
    }

    //Add item
    public void addItem(int items, int info)
    {
        itemNumber = validItemNumber(items, info);
        Item foundItem = null;
        foundItem = validateAllItems(itemNumber, items);
        

    }

    //End of day processing.
    public void endOfDayProc(int items, int info)
    {
        printingInventoryReport(items);
        processAutoOrder(items);
    }

    //Printing the inventory report
    private void printingInventoryReport(int items)
    {
        System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s \n", "Item", "Item", " ",
            " ", " ", "Unit", "Item");

        System.out.printf("%-8s %-10s %-10s %-10s %-10s %-9s %-11s \n\n","Number", "Name", "On Hand",
            "Committed", "On Order", "Price", "Value");
        for(int i = 0; i < items; i++)
        {
            itemInfo = item_List.get(i).getItemNo();
            itemNameInfo = item_List.get(i).getItemName();
            onHandInfo = item_List.get(i).getOnHand();
            committedInfo = item_List.get(i).getCommitted();
            onOrderInfo = item_List.get(i).getOnOrder();
            unitPriceInfo = item_List.get(i).getUnitPrice();
            reorderInfo = item_List.get(i).getReorderPoint();
            econOrderQtyInfo = item_List.get(i).getEconOrderQty();
            System.out.printf ("%-8s %-10s %-10d %-10d %-10d $ %-7.2f $ %-10.2f \n",
                itemInfo, itemNameInfo, onHandInfo, committedInfo, onOrderInfo, unitPriceInfo,
                unitPriceInfo * (onHandInfo + committedInfo));
        }
    }

    //Process Auto Order
    public void processAutoOrder(int items)
    {
        boolean processAutoOrder = false;
        for(int i = 0; i < items; i++)
        {
            onHandInfo = item_List.get(i).getOnHand();
            processAutoOrder = item.autoOrder(onHandInfo);
            if(processAutoOrder != false)
            {
                itemInfo = item_List.get(i).getItemNo();
                itemNameInfo = item_List.get(i).getItemName();
                onOrderInfo = item_List.get(i).getOnOrder();
                econOrderQtyInfo = item_List.get(i).getEconOrderQty();
                System.out.println ("Ordered " +  econOrderQtyInfo + " of item " + itemInfo + "  " + itemNameInfo);
                System.out.println ("on order amount is " + onOrderInfo);
            }
        }
    }
}