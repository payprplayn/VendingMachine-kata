package test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Coin;
import main.VendingMachine;
import main.VendingMachine.Product;

public class VendingMachineTest {
	VendingMachine vendingMachine;
	private enum CoinType implements Coin{
		PENNY(2.5,1.55,19.05,1), QUARTER(5.67,1.75,24.26,25), NICKEL(5.0,1.95,21.21,5), DIME(2.268, 1.35, 17.91,10);
		private double diameter;
		private double thickness;
		private double weight;
		private int value;

		CoinType(double weight,double thickness, double diameter, int value){
			this.weight=weight;
			this.thickness=thickness;
			this.diameter=diameter;
			this.value=value;
		}
		@Override
		public double getWeightInGrams() {
			return weight;
		}

		@Override
		public double getThicknessInMillimeters() {
			return thickness;
		}

		@Override
		public double getDiameterInMillimeters() {
			return diameter;
		}
	}
	@Before
	public void setUp(){
		vendingMachine=new VendingMachine();
		vendingMachine.setCoinReturn(new LinkedList<Coin>());
		vendingMachine.setVendTarget(new LinkedList<VendingMachine.Product>());
		Collection<Coin> input= new LinkedList<Coin>();
		input.add(CoinType.DIME);
		vendingMachine.addDimes(input);
		input.clear();
		input.add(CoinType.NICKEL);
		vendingMachine.addNickels(input);
	}
	@After
	public void cleanUp(){
		vendingMachine.setAmount(VendingMachine.Product.CANDY, 10);
	}
	@Test
	public void displayReadsInsertCoinWhenNoCoinsHaveBeenInserted() {
		assertEquals(vendingMachine.readDisplay(),"INSERT COIN");
	}
	
	@Test
	public void vendingMachineRejectsPennies(){
		vendingMachine.insert(CoinType.PENNY);
		assert(vendingMachine.getCoinReturn().contains(CoinType.PENNY));
	}
	
	@Test
	public void vendingMachineAcceptsQuarters(){
		vendingMachine.insert(CoinType.QUARTER);
		assert(!vendingMachine.getCoinReturn().contains(CoinType.QUARTER));
	}
	
	@Test
	public void vendingMachineAcceptsNickels(){
		vendingMachine.insert(CoinType.NICKEL);
		assert(!vendingMachine.getCoinReturn().contains(CoinType.NICKEL));
	}
	@Test
	public void vendingMachineAcceptsDimes(){
		vendingMachine.insert(CoinType.DIME);
		assert(!vendingMachine.getCoinReturn().contains(CoinType.DIME));
	}
	@Test
	public void displayedTotalIncreasesBy25WhenQuarterIsAccepted(){
		vendingMachine.insert(CoinType.QUARTER);
		assertEquals("$0.25",vendingMachine.readDisplay());
		vendingMachine.insert(CoinType.QUARTER);
		assertEquals("$0.50",vendingMachine.readDisplay());
	}
	@Test
	public void displayedTotalIncreasesBy5WhenNickelIsAccepted(){
		vendingMachine.insert(CoinType.NICKEL);
		assertEquals("$0.05",vendingMachine.readDisplay());
		vendingMachine.insert(CoinType.NICKEL);
		assertEquals("$0.10",vendingMachine.readDisplay());
	}
	@Test
	public void displayedTotalIncreasesBy5WhenDimeIsAccepted(){
		vendingMachine.insert(CoinType.DIME);
		assertEquals("$0.10",vendingMachine.readDisplay());
		vendingMachine.insert(CoinType.DIME);
		assertEquals("$0.20",vendingMachine.readDisplay());
	}
	@Test
	public void coinsAreReturnedWhenCoinReturnButtonIsPushed(){
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.returnCoins();
		assert vendingMachine.getCoinReturn().remove(CoinType.DIME);
		assert vendingMachine.getCoinReturn().remove(CoinType.QUARTER);
		assert vendingMachine.getCoinReturn().isEmpty();
	}
	@Test
	public void vendingMachineSellsCola(){
		for (int i=0; i<4; i++)vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.order(VendingMachine.Product.COLA);
		assert vendingMachine.getVendTarget().contains(VendingMachine.Product.COLA);
	}
	@Test
	public void vendingMachineSellsChips(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.order(VendingMachine.Product.CHIPS);
		assert vendingMachine.getVendTarget().contains(VendingMachine.Product.CHIPS);
	}
	@Test
	public void vendingMachineSellsCandy(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.NICKEL);
		vendingMachine.order(VendingMachine.Product.CANDY);
		assert vendingMachine.getVendTarget().contains(VendingMachine.Product.CANDY);
	}
	@Test
	public void vendingMachineKeepsMoneyAfterSale(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.NICKEL);
		vendingMachine.order(VendingMachine.Product.CANDY);
		vendingMachine.returnCoins();
		assert vendingMachine.getCoinReturn().isEmpty();
	}
	@Test
	public void vendingMachineDisplaysTHANKYOUAfterSale(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.NICKEL);
		vendingMachine.order(VendingMachine.Product.CANDY);
		assertEquals(vendingMachine.readDisplay(),"THANK YOU");
	}
	@Test
	public void vendingMachineDisplaysINSERTCOINAfterTHANKYOU(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.NICKEL);
		vendingMachine.order(VendingMachine.Product.CANDY);
		vendingMachine.readDisplay();
		assertEquals(vendingMachine.readDisplay(),"INSERT COIN");
	}
	@Test
	public void vendingMachineDisplaysSOLDOUTIfProductIsUnavailable(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.NICKEL);
		vendingMachine.setAmount(Product.CANDY, 0);
		vendingMachine.order(VendingMachine.Product.CANDY);
		assertEquals(vendingMachine.readDisplay(),"SOLD OUT");
	}
	@Test
	public void vendingMachineDisplayRetunsToPreviousMessageAfterDisplayingSOLDOUT(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.NICKEL);
		vendingMachine.setAmount(Product.CANDY, 0);
		vendingMachine.order(VendingMachine.Product.CANDY);
		vendingMachine.readDisplay();
		assertEquals(vendingMachine.readDisplay(),"$0.65");
	}
	@Test
	public void vendingMachineDoesNotDispenseItemIfBalanceIsTooLow(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.setAmount(Product.CANDY, 0);
		vendingMachine.order(VendingMachine.Product.CANDY);
		assert vendingMachine.getVendTarget().isEmpty();
	}
	
	@Test
	public void vendingMachineReturnsChangeAfterItemIsDispensed(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.insert(CoinType.NICKEL);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.order(VendingMachine.Product.CANDY);
		assertEquals(25, value(vendingMachine.getCoinReturn()));
	}

	@Test
	public void vendingMachineDisplaysEXACTCHANGEONLYwhenExactChangeIsRequired(){
		vendingMachine.collectCoins();
		assertEquals(vendingMachine.readDisplay(),"EXACT CHANGE ONLY");
	}
	private int value(Collection<? super Coin> coinReturn) {
		int value=0;
		for(CoinType coin : CoinType.values()){
			while(coinReturn.remove(coin)) value+=coin.value;
		}
		return value;
	}

	

}
