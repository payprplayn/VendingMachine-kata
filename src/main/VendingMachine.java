package main;

import java.util.Collection;
import java.util.LinkedList;

public class VendingMachine {

	private Collection<? super Coin> coinReturn;
	private int balance;
	private Collection<Coin> depositedCoins;
	private Collection<? super Product> vendTarget;
	/**
	 * set the vend target for this vending machine.
	 * @param vendTarget the new vend target. Any future products vended will be placed here (until a new vendTarget is set);
	 */
	public void setVendTarget(Collection<? super Product> vendTarget) {
		this.vendTarget = vendTarget;
	}
	public enum Product{
		COLA(1.0), CHIPS(0.5), CANDY(.65);
		private double price;
		Product(double price){
			this.price=price;
		}
	}
	public VendingMachine(){
		balance=0;
		depositedCoins=new LinkedList<Coin>();
		
	}
	/**
	 * @return the current contents of this VendingMachine's display
	 */
	public String readDisplay() {
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
			depositedCoins.add(coin);
		}
		else if(coin.getWeightInGrams()==5.0 && coin.getDiameterInMillimeters()==21.21 && coin.getThicknessInMillimeters()==1.95){//detect a nickel
			balance+=5;
			depositedCoins.add(coin);
		}
		else if(coin.getWeightInGrams()==2.268 && coin.getDiameterInMillimeters()==17.91 && coin.getThicknessInMillimeters()==1.35){//detect a dime
			balance+=10;
			depositedCoins.add(coin);
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
		coinReturn.addAll(depositedCoins);
		depositedCoins.clear();
	}
	/**
	 * Order a product.  if sufficient money has been inserted, the product will be vended and any change will be returned.
	 * Otherwise, the price of the product will be displayed.
	 * @param product the product to order
	 */
	public void order(Product product) {
		if (balance>=product.price){
			vendTarget.add(product);
			depositedCoins.clear();
		}
	}
	/**
	 * @return The vend target that was previously set by setVendTarget()
	 */
	public Collection<? super Product > getVendTarget() {
		return vendTarget;
	}

}
