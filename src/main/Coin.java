package main;

public enum Coin {
	PENNY(1), QUARTER(25);
	Coin(int value){
		this.value=value;
	}
	int value;
}
