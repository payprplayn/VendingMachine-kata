package main;

/**
 * basic Coin interface for use with VendingMachine
 * @author Gabriel Burns
 *
 */
public interface Coin {
	/**
	 * 
	 * @return the weight of the coin in grams
	 */
	public double getWeightInGrams();
	/**
	 * 
	 * @return the thickness of the coin in mm
	 */
	public double getThicknessInMillimeters();
	/**
	 * 
	 * @return the diameter of the coin in mm
	 */
	public double getDiameterInMillimeters();

}
