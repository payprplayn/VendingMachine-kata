package main;

import java.util.Collection;
import java.util.LinkedList;

public class VendingMachine {
	
	public Collection<Coin> coinReturn;
	private int balance;
	
	public VendingMachine (){
		coinReturn=new LinkedList<Coin>();
	}

	public String readDisplay() {
		if (balance!=0) return "$"+ String.format("%.2f", balance*0.01);
		return "INSERT COIN";
	}

	public void insert(Coin coin) {
		if(coin==Coin.PENNY) coinReturn.add(coin);
		else{
			balance+=coin.value;
		}
		
	}

}
