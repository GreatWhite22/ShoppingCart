package Assignment3;

public class Item 
{
	protected String name;
	protected float price;
	protected int quantity;
	protected int weight;
	protected double SALES_TAX = 0.1;
	protected double PREMIUM_SHIPPING = 0.2;

	Item(String givenName, float givenPrice, int givenQuantity, int givenWeight){
		name = givenName;
		price = givenPrice;
		quantity = givenQuantity;
		weight = givenWeight;
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		int shipping_price = 20 * weight * quantity;
		final_price += price;
		final_price += shipping_price;
		return final_price;
	}
	

	void printItemAttributes () 
	{
		System.out.println(name + " " + price + " " + quantity + " " + weight);
	}
}
