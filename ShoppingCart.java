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
			//check for valid input
			//if (valid){
			executeTransaction(input);
		} 
		
		public void executeTransaction(String input[]){
			String operation = input[0];
			switch(operation){
			case "insert":
				insertItem(input);
				//sort cart
				break;
			case "search":
				searchItem(input);
				break;
			case "delete":
				deleteItem(input);
				break;
			case "print":
				printCart();
				break;
			}
		}
		
		void insertItem(String input[]){
			String[] states = new String[]{"TX", "NM", "VA", "AZ", "AK"};
			String category = input[1];
			String name = input[2];
			float price = Float.parseFloat(input[3]);
			int quantity = Integer.parseInt(input[4]);
			int weight = Integer.parseInt(input[5]);
			boolean fragile = false;
			boolean salesTax = false;
			boolean perishable = false;
			if(category == "electronics"){
				if(input[6] == "F"){
						fragile = true;
				}
				for(String s: states){
					if(input[7].contains(s)){ 
						salesTax = true;
					}
				}
				Item newItem = new Electronics(name, price, quantity, weight, fragile, salesTax);
				newItem.printItemAttributes();
				cart.add(newItem);
			}
			else if(category == "groceries"){
				if(input[6] == "P"){
					perishable = true;
				}
				Item newItem = new Grocery(name, price, quantity, weight, perishable);
				newItem.printItemAttributes();
				cart.add(newItem);
			}
			else{
			Item newItem = new Item(name, price, quantity, weight);
			cart.add(newItem);
			}
		}
		
		void searchItem(String[] input){
			String searchName = input[1];
			int numberOfInstances = 0;
			for(int i = 0; i < cart.size(); i++){
				if(cart.get(i).name == searchName){
					numberOfInstances++;
				}
			}
			System.out.println(searchName + " " + numberOfInstances);
		}
		
		void deleteItem(String[] input){
			String deleteName = input[1];
			int numberOfInstances = 0;
			for(int i = 0; i < cart.size(); i++){
				if(cart.get(i).name == deleteName){
					cart.remove(i);
					numberOfInstances++;
				}
			}
			System.out.println(deleteName + " " + numberOfInstances);
		}
		
		void updateItem(String[] input){
			String updateName = input[1];
			int newQuantity = Integer.parseInt(input[3]);
			for(int i = 0; i < cart.size(); i++){
				if(cart.get(i).name == updateName){
					cart.get(i).quantity = newQuantity;
					System.out.println(updateName + " " + newQuantity);
					return;
				}
			}
		}
		void printCart(){
			for(int i = 0; i < cart.size(); i++){
				Item printItem = cart.get(i);
				System.out.println(printItem.name + " " + printItem.quantity + " " + printItem.calculatePrice());
			}
		}
		
//		public void InsertionSort(Item newItem)
//		{
//		     int j;
//		     int i;  
//
//		     for (j = 0; j < cart.size(); j++){
//		           newItem = cart.get(j);
//		           for(i = j - 1; (i >= 0) && (num[ i ] < key); i--)   // Smaller values are moving up
//		          {
//		                 num[ i+1 ] = num[ i ];
//		          }
//		         num[ i+1 ] = key;    // Put the key in its proper location
//		     }
//		}
}
