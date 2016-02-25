/*# ShoppingCart
EE 422C assignment 3 - Shopping Cart
Group #46 
Connor Lewis - csl735, Salim Memon - sam6345*/

package Assignment3;

public class Grocery extends Item {
	private boolean perishable;
	
	Grocery(String givenName, float givenPrice, int givenQuantity, int givenWeight,
			boolean givenFragile) {
		super(givenName, givenPrice, givenQuantity, givenWeight);
		perishable = givenFragile;
	}
	
	/**
	 * @return the perishable
	 */
	public boolean isPerishable() {
		return perishable;
	}

	/**
	 * @param perishable the perishable to set
	 */
	public void setPerishable(boolean perishable) {
		this.perishable = perishable;
	}

	float calculatePrice()			// calculates final price based on shipping cost price and then price per item plus premium shipping if included
	{
		float final_price = 0;
		int shipping_price = 20 * weight * quantity;
		final_price += (price * quantity);
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
