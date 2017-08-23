package test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import main.Coin;
import main.VendingMachine;

public class VendingMachineTest {
	VendingMachine vendingMachine;
	private enum CoinType implements Coin{
		PENNY(2.5,1.55,19.05), QUARTER(5.67,1.75,24.26), NICKEL(5.0,1.95,21.21), DIME(2.268, 1.35, 17.91);
		private double diameter;
		private double thickness;
		private double weight;

		CoinType(double weight,double thickness, double diameter){
			this.weight=weight;
			this.thickness=thickness;
			this.diameter=diameter;
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
		vendingMachine.order(VendingMachine.Product.CANDY);
		assert vendingMachine.getVendTarget().contains(VendingMachine.Product.CANDY);
	}
	@Test
	public void vendingMachineKeepsMoneyAfterSale(){
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.QUARTER);
		vendingMachine.insert(CoinType.DIME);
		vendingMachine.order(VendingMachine.Product.CANDY);
		vendingMachine.returnCoins();
		assert vendingMachine.getCoinReturn().isEmpty();
	}

	

}
