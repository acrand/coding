package my.zipcodes.exceptions;

public class InvalidZipCode extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2944878235528309136L;
	
	String errorCode;
	
	public InvalidZipCode(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;

	}

	public String getErrorCode() {
		return errorCode;
	}

}
