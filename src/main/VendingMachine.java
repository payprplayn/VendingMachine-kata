package main;

import java.util.Collection;
import java.util.Stack;

public class VendingMachine {

	private Collection<? super Coin> coinReturn;
	private int balance;
	private Collection<Coin> depositedQuarters;
	private Collection<Coin> depositedNickels;
	private Collection<Coin> depositedDimes;
	private Stack<Coin> quarters;
	private Stack<Coin> nickels;
	private Stack<Coin> dimes;
	private Collection<? super Product> vendTarget;
	private boolean productPurchased;
	private boolean soldOut;
	private static final int DEFAULT_PRODUCT_AMOUNT=10;
	/**
	 * set the vend target for this vending machine.
	 * @param vendTarget the new vend target. Any future products vended will be placed here (until a new vendTarget is set);
	 */
	public void setVendTarget(Collection<? super Product> vendTarget) {
		this.vendTarget = vendTarget;
	}
	public enum Product{
		COLA(100), CHIPS(50), CANDY(65);
		private final int price;
		private int amount;
		Product(int price){
			this.price=price;
			this.amount=DEFAULT_PRODUCT_AMOUNT;
		}
		
	}
	public void setAmount(Product product, int amount){
		product.amount=amount;
	}
	public VendingMachine(){
		balance=0;
		depositedQuarters=new Stack<Coin>();
		depositedNickels=new Stack<Coin>();
		depositedDimes=new Stack<Coin>();
		quarters=new Stack<Coin>();
		nickels=new Stack<Coin>();
		dimes=new Stack<Coin>();
		
	}
	/**
	 * @return the current contents of this VendingMachine's display
	 */
	public String readDisplay() {
		if(productPurchased){
			productPurchased=false;
			return "THANK YOU";
		}
		if(soldOut){
			soldOut=false;
			return "SOLD OUT";
		}
		if(balance!=0) return String.format("$%.2f", balance*.01);
		return "INSERT COIN";
	}
	
	/**
	 * inert a coin into the VendingMachine
	 * @param coin
	 */
	public void insert(Coin coin) {
		if(coin.getWeightInGrams()==5.67 && coin.getDiameterInMillimeters()==24.26 && coin.getThicknessInMillimeters()==1.75){//detect a Quarter
			balance+=25;
			depositedQuarters.add(coin);
		}
		else if(coin.getWeightInGrams()==5.0 && coin.getDiameterInMillimeters()==21.21 && coin.getThicknessInMillimeters()==1.95){//detect a nickel
			balance+=5;
			depositedNickels.add(coin);
		}
		else if(coin.getWeightInGrams()==2.268 && coin.getDiameterInMillimeters()==17.91 && coin.getThicknessInMillimeters()==1.35){//detect a dime
			balance+=10;
			depositedDimes.add(coin);
		}
		else coinReturn.add(coin);
		
	}
	
	/**
	 * Sets the coin return destination for this VendingMachine.
	 * @param target 
	 * the new coin return destination.  Any future coins returned by this Vending Machine will be added to this Collection.
	 * Must support add().
	 */
	public void setCoinReturn(Collection<? super Coin> target) {
		coinReturn=target;	
	}
	/**
	 * @return the coin return destination for this VendingMachine. Coins returned by this vending machine will be added to this.
	 */
	public Collection<? super Coin> getCoinReturn() {
		return coinReturn;
	}
	
	/**
	 * press the coin return button on this Vending Machine
	 */
	public void returnCoins() {
		balance=0;
		coinReturn.addAll(depositedQuarters);
		coinReturn.addAll(depositedNickels);
		coinReturn.addAll(depositedDimes);
		depositedQuarters.clear();
		depositedNickels.clear();
		depositedDimes.clear();
	}
	/**
	 * Order a product.  if sufficient money has been inserted, the product will be vended and any change will be returned.
	 * Otherwise, the price of the product will be displayed.
	 * @param product the product to order
	 */
	public void order(Product product) {
		if (product.amount==0){
			soldOut=true;
		}
		else if (balance>=product.price){
			vendTarget.add(product);
			quarters.addAll(depositedQuarters);
			dimes.addAll(depositedDimes);
			nickels.addAll(depositedNickels);
			depositedQuarters.clear();
			depositedNickels.clear();
			depositedDimes.clear();
			productPurchased=true;
			makeChange(balance-product.price);
			balance=0;
		}
	}
	private void makeChange(int amount){
		while (amount>=25&&!quarters.isEmpty()){
			amount-=25;
			coinReturn.add(quarters.pop());
		}
		while (amount>=10&&!dimes.isEmpty()){
			amount-=10;
			coinReturn.add(dimes.pop());
		}
		while (amount>=5&&!nickels.isEmpty()){
			amount-=5;
			coinReturn.add(nickels.pop());
		}
	}
	/**
	 * @return The vend target that was previously set by setVendTarget()
	 */
	public Collection<? super Product > getVendTarget() {
		return vendTarget;
	}
	
	/**
	 * Add Quarters to the Vending Machine
	 * @param toAdd quarters to add to the machine
	 */
	public void addQuarters(Collection<? extends Coin> toAdd){
		quarters.addAll(toAdd);
		
	}
	/**
	 * Add dimes to the Vending Machine
	 * @param toAdd dimes to add to the machine
	 */
	public void addDimes(Collection<? extends Coin> toAdd){
		dimes.addAll(toAdd);
		
	}
	/**
	 * Add nickels to the Vending Machine
	 * @param toAdd nickels to add to the machine
	 */
	public void addNickels(Collection<? extends Coin> toAdd){
		nickels.addAll(toAdd);
		
	}

}
