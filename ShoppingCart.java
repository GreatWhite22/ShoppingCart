/*# ShoppingCart
EE 422C assignment 3 - Shopping Cart
Group #46 
Connor Lewis - csl735, Salim Memon - sam6345*/

package Assignment3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShoppingCart 
	{
	ArrayList<Item> cart = new ArrayList<Item>();
	  public static void main(String[] args) 
	  {
		  if (args.length != 1) 
			{
				System.err.println ("Error: Incorrect number of command line arguments");
				System.exit(-1);
			}
			processLinesInFile (args[0]);
			
		}

		/******************************************************************************
		* Method Name: processLinesInFile                                             *
		* Purpose: Opens the file specified in String filename, reads each line in it *
		*          Invokes translate () on each line in the file, and prints out the  *
		*          translated piglatin string.                                        *
		* Returns: None                                                               *
		******************************************************************************/
		public static void processLinesInFile (String filename) 
		{ 

			ShoppingCart cart = new ShoppingCart(); 
			try 
			{
				FileReader freader = new FileReader(filename);
				BufferedReader reader = new BufferedReader(freader);
				
				for (String s = reader.readLine(); s != null; s = reader.readLine()) 
				{
					cart.parse(s);
				}
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println ("Error: File not found. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) 
			{
				System.err.println ("Error: IO exception. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}
	  }
		/******************************************************************************
		* Method Name: parse                                                      *
		* Purpose: Converts String inputString into piglatin based on rules specified *
		*          in your assignment write-up.                                       *
		* Returns: String object containing the piglatin translation of               *
		*          String inputString                                                 *
		******************************************************************************/
		
		public void parse (String inputString){
			String[] input;
			input = inputString.split("\\s+");
			executeTransaction(input);
		} 
		
		public void executeTransaction(String input[]){
			String operation = input[0];
			boolean Validity = true;						
			switch(operation){
			case "insert":
				Validity = insertItem(input);					//if return value is true then process went through as expected else return to get next input
				if (Validity == false)							
					break;										
				Collections.sort(cart, new Comparator<Item>() {			// sorting arraylist to keep in alphabetical order
				    @Override
				    public int compare(Item i1, Item i2) {
				    	String name1 = i1.name.toLowerCase();
				    	String name2 = i2.name.toLowerCase();
				    	return name1.compareTo(name2);
				    }
				});
				break;
			case "search":
				searchItem(input);
				break;
			case "update":
				updateItem(input);
				break;
			case "delete":
				deleteItem(input);
				break;
			case "print":
				printCart();
				break;
			}
		}
		
		boolean insertItem(String input[]){
			try{										
			String[] states = new String[]{"tx", "nm", "va", "az", "ak"}; //list of all the states that will be not be included in sales tax
			String OtherStates = new String();
			OtherStates = "AL AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS MO MT NE NV NH NJ NY NC ND OH OK OR PA RI SC SD TN UT VT WA WV WI WY";
			String category = input[1];
			String name = input[2];
			float price = Float.parseFloat(input[3]);
			int quantity = Integer.parseInt(input[4]);
			if (quantity < 0){
				System.out.println ("Error: Illegal input. This request has been abandoned...");				// if the quantity is less than zero then this is an incorrect input and must request new input
				return false;
			}
			int weight = Integer.parseInt(input[5]);
			if (weight < 0){
				System.out.println ("Error: Illegal input. This request has been abandoned...");				// the weight cannot be less than zero so request new input
				return false;
			}
			boolean fragile = false;
			boolean salesTax = true;
			boolean perishable = false;	// setting variables based on input and also setting fragile and salesTax + perishables for later
			if((category.toLowerCase()).equals("electronics")){
				if((input[6].toLowerCase()).equals("f")){		// fragile requirement set to lower case if nf or f not in input then incorrect input request new input
						fragile = true;
				}
				else if((input[6].toLowerCase()).equals("nf")){
					fragile = false;
				}
				else{
					System.out.println ("Error: must be F/NF for input. This request has been abandoned...");				
					return false;
				}
				boolean ValidState = false;
				for(String s: states){		//comparing to check if there should be sales tax or not
					if((input[7].toLowerCase()).contains(s)){ 					// state requirment set to lower case, if state does not match then regular sales tax applied
						salesTax = false;
						ValidState = true;
						break;
					}
					else if((OtherStates.toLowerCase()).contains((input[7].toLowerCase()))){	// check to see if the state actually exists or not
						ValidState = true;
					}
				}
				if (ValidState == false){
					System.out.println ("Error: must be a valid state for input. This request has been abandoned...");				
					return false;
				}
				Item newItem = new Electronics(name, price, quantity, weight, fragile, salesTax);
				newItem.printItemAttributes();
				cart.add(newItem);
			}
			else if((category.toLowerCase()).equals("groceries")){
				if((input[6].toLowerCase()).equals("p")){ 		// preishable requirments set to lower case, if p or np does not exist then wrong input request new input
					perishable = true;
				}
				else if((input[6].toLowerCase()).equals("np")){
					perishable = false;
				}
				else{
					System.out.println ("Error: must be P/NP for input. This request has been abandoned...");				
					return false;
				}
				Item newItem = new Grocery(name, price, quantity, weight, perishable);
				newItem.printItemAttributes();
				cart.add(newItem);
			}
			else if((category.toLowerCase()).equals("clothing")){
			Item newItem = new Clothing(name, price, quantity, weight);				 
			newItem.printItemAttributes();
			cart.add(newItem);
			}
			else{
				System.out.println ("Error: Illegal input. This request has been abandoned...");				// its not electronics groceries or clothing so incorrect input request new input
				return false;
			}
		}
			catch(Exception e){				
				System.out.println ("Error: Illegal input. This request has been abandoned...");				
				return false;
			}
			return true;
		}
		
		void searchItem(String[] input){
			try{
			String searchName = input[1];
			int numberOfInstances = 0;
			for(int i = 0; i < cart.size(); i++){			// running throught the shopping cart to check if the name matches or not and returns the total quantity
				if(cart.get(i).name.equals(searchName)){
					numberOfInstances += cart.get(i).getQuantity();
					//numberOfInstances++;
				}
			}
			System.out.println("Search: " + searchName + " " + numberOfInstances);
			}
			catch(Exception e){				
				System.out.println ("Error: Illegal input. This request has been abandoned...");				
				return;
			}
			return;
		}
		
		void deleteItem(String[] input){
			try{
			String deleteName = input[1];
			int numberOfInstances = 0;
			for(int i = 0; i < cart.size(); i++){				// runs through the shopping cart and gets rid of all the instances with the specific name
				if(cart.get(i).name.equals(deleteName)){
					numberOfInstances += cart.get(i).quantity;
					cart.remove(i);
					i = i-1;
				}
			}
			System.out.println("Delete: " + deleteName + " " + numberOfInstances);
		}
			catch(Exception e){				
				System.out.println ("Error: Illegal input. This request has been abandoned...");				
				return;
			}
			return;
		}
		
		void updateItem(String[] input){
			try{
			String updateName = input[1];
			int newQuantity = Integer.parseInt(input[2]);
			for(int i = 0; i < cart.size(); i++){					// runs through the shopping cart and updates the value of the item
				if(cart.get(i).name.equals(updateName)){
					cart.get(i).quantity = newQuantity;
					System.out.println("Update: " + updateName + " " + newQuantity);
					return;
				}
			}
		}
			catch(Exception e){				
				System.out.println ("Error: Illegal input. This request has been abandoned...");				
				return;
			}
			return;
		}
		
		void printCart(){
			try{
			for(int i = 0; i < cart.size(); i++){			// prints the details of each item in the cart, with its final price calculated as well
				Item printItem = cart.get(i);
				System.out.println(printItem.name + " " + printItem.quantity + " $" + printItem.calculatePrice());
			}
		}
			catch(Exception e){				//////////forced in
				System.out.println ("Error: Illegal input. This request has been abandoned...");				
				return;
			}
			return;
		}
}

