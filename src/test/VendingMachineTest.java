package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

}
