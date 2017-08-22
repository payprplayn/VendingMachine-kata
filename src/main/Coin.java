package main;

public enum Coin {
	PENNY(1), QUARTER(25), DIME(10), NICKEL(5);
	Coin(int value){
		this.value=value;
	}
	int value;
}
