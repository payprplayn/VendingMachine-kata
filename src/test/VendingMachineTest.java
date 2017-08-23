package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import main.Coin;
import main.VendingMachine;

public class VendingMachineTest {
	VendingMachine vendingMachine;
	private enum CoinTypes implements Coin{
		PENNY(2.5,1.55,19.05), QUARTER(5.67,1.75,24.26), NICKEL(5.0,1.95,21.21), DIME(2.268, 1.35, 17.91);
		private double diameter;
		private double thickness;
		private double weight;

		CoinTypes(double weight,double thickness, double diameter){
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
	}

	@Test
	public void displayReadsInsertCoinWhenNoCoinsHaveBeenInserted() {
		assertEquals(vendingMachine.readDisplay(),"INSERT COIN");
	}
	
	@Test
	public void vendingMachineRejectsPennies(){
		vendingMachine.insert(CoinTypes.PENNY);
		assert(vendingMachine.getCoinReturn().contains(CoinTypes.PENNY));
	}
	
	@Test
	public void vendingMachineAcceptsQuarters(){
		vendingMachine.insert(CoinTypes.QUARTER);
		assert(!vendingMachine.getCoinReturn().contains(CoinTypes.QUARTER));
	}
	
	@Test
	public void vendingMachineAcceptsNickels(){
		vendingMachine.insert(CoinTypes.NICKEL);
		assert(!vendingMachine.getCoinReturn().contains(CoinTypes.NICKEL));
	}
	@Test
	public void vendingMachineAcceptsDimes(){
		vendingMachine.insert(CoinTypes.DIME);
		assert(!vendingMachine.getCoinReturn().contains(CoinTypes.DIME));
	}
	

}
