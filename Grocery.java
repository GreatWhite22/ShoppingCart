package Assignment3;

public class Grocery extends Item {
	private boolean perishable;
	
	Grocery(String givenName, float givenPrice, int givenQuantity, int givenWeight,
			boolean givenFragile) {
		super(givenName, givenPrice, givenQuantity, givenWeight);
		perishable = givenFragile;
	}
	
	float calculatePrice()
	{
		float final_price = 0;
		int shipping_price = 20 * weight * quantity;
		final_price += price;
		final_price += price * SALES_TAX;
		final_price += shipping_price;
		if(perishable){
			final_price += shipping_price * PREMIUM_SHIPPING;
		}
		return final_price;
	}
	
	void printItemAttributes () 
	{
		System.out.println(name + " " + price + " " + quantity + " " + weight + 
				" perishable=" + perishable);
	}
	
}
