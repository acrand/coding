package my.zipcodes.exceptions;
/**
 * Invalid zipcode exception class for Shipping zip codes
 * @author acrand
 *
 */
public class InvalidZipCodeRange extends InvalidZipCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8840178290447669523L;

	public InvalidZipCodeRange( String errorCode, String message) {
		super(errorCode, message);
		
	}


}
