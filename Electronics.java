package Assignment3;

public class Electronics extends Item 
{
	private boolean fragile;
	private boolean salesTax;
	
	Electronics(String givenName, float givenPrice, int givenQuantity, int givenWeight,
			boolean givenFragile, boolean givenSalesTax) {
		super(givenName, givenPrice, givenQuantity, givenWeight);
		fragile = givenFragile;
		salesTax = givenSalesTax;
	}
	
	float calculatePrice () 		// calculates final price based on the shipping cost with added sales tax if appropriate state and then if fragile then add premium shipping + price per item
	{
		float final_price = 0;
		int shipping_price = 20 * weight * quantity;
		final_price += (price * quantity);
		if(salesTax){
			final_price += price * SALES_TAX;
		}
		final_price += shipping_price;
		if(fragile){
			final_price += shipping_price * PREMIUM_SHIPPING;
		}
		return final_price;
	}

	void printItemAttributes () 
	{
		System.out.println(name + " " + price + " " + quantity + " " + weight + " fragile="
			+ fragile + " sales tax=" + salesTax);
	}
}
