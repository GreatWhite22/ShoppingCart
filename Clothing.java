package Assignment3;

public class Clothing extends Item 
{
	Clothing(String givenName, float givenPrice, int givenQuantity, int givenWeight) {
		super(givenName, givenPrice, givenQuantity, givenWeight);
	}

	float calculatePrice () 		// calculates final price based on shipping cost + sales tax + price per quantity
	{
		float final_price = 0;
		int shipping_price = 20 * weight * quantity;
		final_price += (price * quantity);
		final_price += price * SALES_TAX;
		final_price += shipping_price;
		return final_price;
	}
}
