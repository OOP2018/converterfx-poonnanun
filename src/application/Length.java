package application;
/**
 * Enum for length that got meter for standart.
 * @author Poonnnaun Poonnopathum
 *
 */
public enum Length {

	/**
	 * Enum member.
	 */
	Meter(1.0),
	Centimeter(0.01),
	Kilometer(1000.0),
	Mile(1604.344),
	Foot(0.3048),
	Wa(2.0),
	AU(149597870700.0);
	
	/**
	 * Attributes of enum members
	 */
	private final double length;
	/**
	 * Enum constructor MUST be private
	 */
	private Length(double length){
		this.length = length;
	}
	
	/**
	 * get the value of member
	 * @return value
	 */
	public double getValue(){
		return this.length;
	}
}
