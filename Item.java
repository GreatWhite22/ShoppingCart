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
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	void printItemAttributes () 
	{
		System.out.println(name + " " + price + " " + quantity + " " + weight);
	}
}
