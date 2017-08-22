package main;

import java.util.Collection;
import java.util.LinkedList;

public class VendingMachine {
	
	public Collection<Coin> coinReturn;
	
	public VendingMachine (){
		coinReturn=new LinkedList<Coin>();
	}

	public String readDisplay() {
		return "INSERT COIN";
	}

	public void insert(Coin coin) {
		if(coin==Coin.PENNY) coinReturn.add(coin);
		
	}

}
