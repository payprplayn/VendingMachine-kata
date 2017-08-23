package main;

import java.util.Collection;

public class VendingMachine {

	private Collection<? super Coin> coinReturn;
	private int balance;
	public VendingMachine(){
		balance=0;
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
		}
		else if(coin.getWeightInGrams()==5.0 && coin.getDiameterInMillimeters()==21.21 && coin.getThicknessInMillimeters()==1.95);
		else if(coin.getWeightInGrams()==2.268 && coin.getDiameterInMillimeters()==17.91 && coin.getThicknessInMillimeters()==1.35);
		else coinReturn.add(coin);
		
	}
	
	/**
	 * Sets the coin return destination for this VendingMachine.
	 * @param target 
	 * the new coin return destination.  Any future coins returned by this Vending Machine will be added to this Collection. 
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

}
