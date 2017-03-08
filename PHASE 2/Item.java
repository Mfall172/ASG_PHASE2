
/**
 * This is the class that defines the Inventory Item of a company 
 * 
 * @author Marlon Fallorin / Matthew Baluyot
 * 
 */
public class Item
{
    // the instance variables (fields)
    //REMEMBER: STRINGS ARE IMMUTABLE.
    private String itemNo;
    private String itemName;
    private int onHand;
    private int committed;
    private int onOrder;
    private double unitPrice;
    private int reorderPoint;
    private int econOrderQty;

    //1. Question why can't the variables be named the same as the instance variables?
    // the constructors
    //no arg constructor
    public Item() 
    {
    }
    // arguments include all instance variables except committed and onOrder
    public Item(String number, String name, int amountOnHand, int theReorderPoint, int theEconOrderQty, double theUnitPrice)
    {
        itemNo = number;
        itemName = name;
        onHand = amountOnHand;
        unitPrice = theUnitPrice;
        reorderPoint = theReorderPoint;
        econOrderQty = theEconOrderQty;
        committed = 0;
        onOrder = 0;
    }
    // arguments include all instance variables
    public Item(String number, String name, int amountOnHand, int isCommitted, int isOnOrder, double theUnitPrice, int theReorderPoint, int theEconOrderQty) 
    {
        itemNo = number;
        itemName = name;
        onHand = amountOnHand;
        committed = isCommitted;
        onOrder = isOnOrder;
        unitPrice = theUnitPrice;
        reorderPoint = theReorderPoint;
        econOrderQty = theEconOrderQty;
    }
    // Copy Constructor (except itemNo, itemName, committed, and onOrder)
    public Item(Item anItem, String number, String name)
    {
        itemNo = number;
        itemName = name;
        onHand = anItem.getOnHand();
        unitPrice = anItem.getUnitPrice();
        committed = 0;
        onOrder = 0;
        reorderPoint = anItem.getReorderPoint();
        econOrderQty = anItem.getEconOrderQty();

    }

    /*
    public Item(int reorderPoint, int econOrderQty, double unitPrice)
    {
    committed = 0;
    onOrder = 0;
    }
     */
    //  the accessors   
    public String getItemNo()
    {
        return itemNo;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getOnHand()
    {
        return onHand;
    }

    public int getCommitted()
    {
        return committed;
    }

    public int getOnOrder()
    {
        return onOrder;
    }

    public double getUnitPrice()
    {
        return unitPrice;
    }

    public int getReorderPoint()
    {
        return reorderPoint;
    }

    public int getEconOrderQty()
    {
        return econOrderQty;
    }

    // the mutators
    public void setItemNo(String num)
    {
        itemNo = num;
    }

    public void setItemName(String name)
    {
        itemName = name;
    }

    public void setOnHand(int amtOH)
    {
        onHand = amtOH;
    }

    public void setUnitPrice(double price)
    {
        unitPrice = price;
    }

    public void setReorderPoint(int rop)
    {
        reorderPoint = rop;
    }

    public void setEconOrderQty(int eoq)
    {
        econOrderQty = eoq;
    }

    // place the processing methods here 
    //order the items from supplier
    public int orderItems(int amount)
    {
        onOrder = onOrder + amount;
        return amount;
    }

    //receive items from supplier
    public int receiveShipment(int amount)
    {
        onHand += amount;
        if(amount <= onOrder)
        {
            onOrder -= amount;
        }
        else
        {
            onOrder = 0;
        }
        return amount;
    }

    //Retruning Items Back
    public int returningItems(int amount)
    {
        if(amount < onHand)
        {
            onHand -= amount;
        }
        else
        {
            amount = onHand;
            onHand = 0;
        }
        return amount;
    }

    //Shipping the Items to Customers
    public int shippingTheItemsToCustomers(int amount)
    {
        if(amount <= committed)
        {
            committed -= amount;
        }
        else if(amount <= committed + onHand)
        {
            onHand = onHand - (amount - committed);
            committed = 0;
        }
        else
        {
            System.out.println("Shipment of: " + itemNo + " cannot be made.");
            System.out.println("Shipment is larger than onHand and Committed.");
        }
        return amount;
    }

    //The Customers Order
    public int customerOrder(int amount)
    {
        if(amount <= onHand)
        {
            onHand -= amount;
            committed += amount;
        }
        else
        {
            int backOrder = amount - onHand;
            amount = onHand;
            committed += onHand;
            onHand = 0;
            orderItems(backOrder);
            System.out.println("Backordered: " + backOrder + " items of item " + itemNo + "  " + itemName);
        }
        return amount;
    }
    	
    //Processing customer returns
    public int customerReturns (int amount)
    {
        onHand += amount;

        return amount;
    }

    public double ItemValue() 
    {
        double value = onHand * committed;
        return value;
    }

    public void ItemDetails()
    {
        System.out.println("Item Number: " + itemNo + "\n" + 
            "Name: " + itemName + "\n" + 
            "On Hand: " + onHand + "\n" + 
            "Committed: " + committed + "\n" + 
            "Item On Order: " + onOrder + "\n" +
            "Unit Price: " + unitPrice + "\n" + 
            "Order From the Supplier: " + reorderPoint + "\n" + 
            "Economic Order Quantity: " + econOrderQty + "\n");
    }

    public double EndDay() 
    {
        double value = ItemValue();
        return value;
    }
    
    //Auto Order
    public boolean autoOrder(int amountOnHand)
    {
        boolean auto = false;
        if(onHand < reorderPoint)
        {
            orderItems(econOrderQty);
            auto = true;
        }
        return auto;
    }
    // place the print and toString methods here
    public String toString() 
    {
        System.out.println("Item No.\t" + "Item Name\t" + "On Hand\t\t" + "Committed\t" + "Item On Order\t" + "Unit Price\t" + "Item Value\t");  
        return "";
    }
}
