package main;

import java.util.Collection;

public class VendingMachine {

	private Collection<? super Coin> coinReturn;

	public String readDisplay() {
		return "INSERT COIN";
	}

	public void insert(Coin coin) {
		if(coin.getWeightInGrams()==5.67 && coin.getDiameterInMillimeters()==24.26 && coin.getThicknessInMillimeters()==1.75);
		else coinReturn.add(coin);
		
	}

	public void setCoinReturn(Collection<? super Coin> target) {
		coinReturn=target;	
	}

	public Collection<? super Coin> getCoinReturn() {
		return coinReturn;
	}

}
