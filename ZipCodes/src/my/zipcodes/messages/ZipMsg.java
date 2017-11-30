package my.zipcodes.messages;

import java.util.Locale;
import java.util.ResourceBundle;

public class ZipMsg {

	public static final String ZIPE000 = "ZIPE000";
	public static final String ZIPE001 = "ZIPE001";
	public static final String ZIPE002 = "ZIPE002";
	public static final String ZIPE003 = "ZIPE003";
	public static final String ZIPE004 = "ZIPE004";
	public static final String ZIPE005 = "ZIPE005";
	public static final String ZIPE006 = "ZIPE006";
	public static final String ZIPE007 = "ZIPE007";
	
	
	private ZipMsg() {}
	
	static ResourceBundle  rb = ResourceBundle.getBundle("my.zipcodes.messages.zip-messages", Locale.getDefault());
	
	public static String getMessage(String key){
		
		return rb.getString(key);
		
	}
}
