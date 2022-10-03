package com.gl.CaseStudy2;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {
    private static String floatToString(double value) {
        String amount = String.format("%.2f", value);
        return amount;
    }
 public static String salesTaxCalculation(double billAmount) {
        double taxAmount = 0.0;
        if (billAmount <= 1000) {
            taxAmount = billAmount * 0.125;
        } else if (billAmount <= 2500) {
            taxAmount = billAmount * 0.10;
        } else {
            taxAmount = billAmount * 0.075;
        }
        String tax = floatToString(taxAmount);
        return tax;
    }
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter Customer Name:");
    	String customername=sc.nextLine();
    	System.out.println("Enter number of items the customer purchases:");
    	int n=Integer.parseInt(sc.nextLine());
    	List<OrderedItem> orderedList=new ArrayList<>();
    	for (int i=0;i<n;i++) {
    	System.out.println("Enter name and quantity (comma separate format) of purchased item no "+(i+1));
    	String str=sc.nextLine();
    	String[] details= str.split(",");
    	String itemName=details[0];
    	String itemQty=details[1];
    	if (ItemData.isAvailable(itemName))
  {
    		SnackItem s=ItemData.getItem(itemName);
    		String rate=s.getRate();
    		String discountRate=s.getDiscountRate();
    		String discountQty=s.getDiscountQty();
    		Double discountAmount=0.0;
    		Double payAmount=Double.parseDouble(rate)*Double.parseDouble(itemQty);
    		 if (Double.parseDouble(itemQty)>=Double.parseDouble(discountQty)) {
    			 discountAmount=payAmount * (Double.parseDouble(discountRate)/100);
    			 payAmount= payAmount-discountAmount;

    		  }    	 
    		 OrderedItem ot=new OrderedItem(itemName,rate,itemQty,floatToString(discountAmount),floatToString(payAmount) );
    	   orderedList.add(ot);
  } 

    else{
    	System.out.println("Item is not Available");
    	}
    	
 }
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
    	Calendar obj = Calendar.getInstance();
    	String date = formatter.format(obj.getTime());
    	System.out.println("\nCustomer Name:"+customername+"\t\t\tDate:"+date);
    	String output=String.format("%-5s %-10s %-10s %-10s %-10s %-10s", "ITEM" ,"RATE","QUANTITY","PRICE","DISCOUNT","AMOUNT");
    	System.out.println(output);
    	Double billAmount=0.0;
    	for (OrderedItem i:orderedList) {
    	String name=i.getItemName();
    	String rate=i.getRate();
    	String qty=i.getOrderQty();
    	SnackItem snackitem=ItemData.getItem(name);
    	Double price=Double.parseDouble(snackitem.getRate())*Double.parseDouble(qty);
    	String dis=i.getDiscountAmount();
    	String amount=i.getPayAmount();
    	billAmount+= Double.parseDouble(amount);
    	String output1=String.format("%-5s %-10s %-10s %-10s %-10s %-10s", name,rate,qty,price,dis,amount);
    	System.out.println(output1);
    	}
    	String tax=salesTaxCalculation(billAmount);
    	Double total=billAmount+Double.parseDouble(tax);
    	System.out.println("\t\t\t\tBill Amount:"+billAmount);
    	System.out.println("\t\t\t\tAdd: Sales Tax:"+tax);
    	System.out.println("\t\t\t\tFinal Amount:"+total);

    	 }

    	}
