/*# ShoppingCart
EE 422C assignment 3 - Shopping Cart
Group #46 
Connor Lewis - csl735, Salim Memon - sam6345*/

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

	/**
	 * @return the fragile
	 */
	public boolean isFragile() {
		return fragile;
	}

	/**
	 * @return the salesTax
	 */
	public boolean isSalesTax() {
		return salesTax;
	}

	/**
	 * @param fragile the fragile to set
	 */
	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	/**
	 * @param salesTax the salesTax to set
	 */
	public void setSalesTax(boolean salesTax) {
		this.salesTax = salesTax;
	}

	void printItemAttributes () 
	{
		System.out.println(name + " " + price + " " + quantity + " " + weight + " fragile="
			+ fragile + " sales tax=" + salesTax);
	}
}
