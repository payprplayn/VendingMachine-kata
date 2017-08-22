package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Coin;
import main.VendingMachine;

public class VendingMachineTest {
	VendingMachine vendingMachine;
	
	@Before
	public void setUp(){
		vendingMachine=new VendingMachine();
	}

	@Test
	public void displayReadsInsertCoinWhenNoCoinsHaveBeenInserted() {
		assertEquals(vendingMachine.readDisplay(),"INSERT COIN");
	}
	
	@Test
	public void vendingMachineRejectsPennies(){
		vendingMachine.insert(Coin.PENNY);
		assert(vendingMachine.coinReturn.contains(Coin.PENNY));
		assertEquals(vendingMachine.readDisplay(),"INSERT COIN");
	}
	
	@Test
	public void vendingMachineAcceptsQuarters(){
		vendingMachine.insert(Coin.QUARTER);
		assertEquals("$0.25",vendingMachine.readDisplay());
	}

}
