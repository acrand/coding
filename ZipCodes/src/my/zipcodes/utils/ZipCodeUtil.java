package my.zipcodes.utils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import my.zipcodes.ZipBST;
import my.zipcodes.ZipRange;
import my.zipcodes.exceptions.InvalidZipCode;
import my.zipcodes.exceptions.InvalidZipCodeRange;
import my.zipcodes.messages.ZipMsg;

/**
 * Utility class for shipping zip codes
 * @author acrand
 *
 */
public class ZipCodeUtil {
	
	private String source = ZipCodeUtil.class.getName();
	
	private Logger logger = Logger.getLogger(source);
	
	private final static int zipCodeLength = 5;
	
	private static volatile ZipCodeUtil instance;

	private ArrayList<int[]> mergedZips;
	
	private ZipBST bTree;
	
	private ZipCodeUtil(){
		//Create array list to contain the merged Zip Codes 
		mergedZips = new ArrayList<int[]>();
		
		//Create zip Binary Search Tree instance to hold the Zip Code range data 
		bTree = new ZipBST();
	}
	
	/**
	 * return singleton instance of utility
	 * @return
	 */
	public synchronized static ZipCodeUtil getInstance(){
		
		if(instance == null){
			instance =  new ZipCodeUtil();
		}
		
		return instance;
	}
	
	/**
	 * reset singleton instance 
	 */
	public synchronized void reset() {
		logger.entering(source, "reset()");
		
		instance = new ZipCodeUtil();
		
		logger.exiting(source, "reset()");
	}
	
	/**
	 * Merge all unique zip code ranges and provide a Collection of zip code ranges
	 * that reflect the merged results of the int array list of zip codes provided
	 * @param zipCodes
	 * @return
	 * @throws InvalidZipCode
	 */
	public ArrayList<int[]> mergeUniqueRanges(int[]... zipCodes) throws InvalidZipCode {
		if(logger.isLoggable(Level.FINEST))
			logger.entering(source,"mergeUniqueRanges(int[]...{0})",zipCodes);
		
		//Process all  zip codes add them to the collection of Zip Codes for this instance of the 
		//zip utility 
		processZipCodes(zipCodes);
		
		if(logger.isLoggable(Level.FINEST))
			logger.exiting(source, "return - mergeUniqueRanges():{0}",mergedZips);
		return mergedZips;
	}
	
	/**
	 * Test if the provided zipCode can be shipped to based on the current instance of the zip utility
	 * @param zipCode
	 * @return 
	 * @throws InvalidZipCode
	 */
	public boolean canShipTo(int zipCode) throws InvalidZipCode{
		if(logger.isLoggable(Level.FINEST))
			logger.entering(source,"canShipTo(int {0})",zipCode);
		boolean contains = false;
		
		validZipCode(zipCode);
		
		for(int[] range: mergedZips){
		
			contains = bTree.get(range[0]).containsBound(zipCode);
			if(contains) break;
		}
		
		if(logger.isLoggable(Level.FINEST))
		logger.exiting(source, "return - canShipTo():{0}",!contains);
		return !contains;
	}

	/**
	 * Print the provide zip Code Ranges
	 * @param nArrs
	 */
	public static void printZipRange(int[]...nArrs){
		
		char comma = 0;
		for(int[] aArr: nArrs){
			if(comma != 0){
				System.out.print(comma);
				printIntArray(aArr);
			}else{
				comma =  ',';
				printIntArray(aArr);	
			}	
			
		}
		System.out.println();
	}
	
	/**
	 * 
	 * @param arr
	 */
	private static void printIntArray(int[] arr){
		char dash = 0;
		for(int i = 0; i < arr.length; i++){
			if(dash != 0){
				System.out.print(dash);
				System.out.print(arr[i]);
			}else{
				dash =  '-';
				System.out.print(arr[i]);
			}	
		
		}
	}

	/**
	 * 
	 * @param r
	 * @throws InvalidZipCode
	 */
	private void processZipCodes(int[][] r) throws InvalidZipCode{
		mergedZips.clear();
		//Test if more than one zipcode range provided to compare 
		if(!( r.length > 0)){
			throw new  InvalidZipCodeRange(ZipMsg.ZIPE000,ZipMsg.getMessage(ZipMsg.ZIPE000));
		}
		
        //Iterate zipcodes ranges
		for (int[]rZ :r){
			//Merge unique range into the true 
			doMergeUniqueRanges(validZipCodeRange(rZ));
			
		}			
		
		mergedZips.addAll(bTree.getAllValues());
		
	}

	/**
	 * 
	 * @param z
	 * @throws InvalidZipCode
	 */
	private void validZipCode(int z) throws InvalidZipCode{
		if(z <= 0){
			throw new InvalidZipCode(ZipMsg.ZIPE007,ZipMsg.getMessage(ZipMsg.ZIPE007));
		}
		if(!(z >= 0 && (int)(Math.log10(z)+ 1) == zipCodeLength)){
			throw new InvalidZipCode(ZipMsg.ZIPE003,ZipMsg.getMessage(ZipMsg.ZIPE003)); //: lower and upper bounds in array is a valid 5 digit zipcode");
		}
	}

	/**
	 * 
	 * @param r
	 * @return
	 * @throws InvalidZipCode
	 */
	private ZipRange validZipCodeRange(int[] r) throws InvalidZipCode{
		ZipRange range = null;
		
		//test is the range array contains more than two items lower and upper bounds
		if(r.length == 0){
			//throw exception if the range is not valid 
			throw new InvalidZipCodeRange(ZipMsg.ZIPE006,ZipMsg.getMessage(ZipMsg.ZIPE006));
		
		}else if(r.length > 2){
			//throw exception if the range is not valid 
			throw new InvalidZipCodeRange(ZipMsg.ZIPE001, ZipMsg.getMessage(ZipMsg.ZIPE001));//: range array contains more than two items lower and upper bounds");

		}else{

			//Test if range bound is less than ZERO
			if(r[0] < 0){
				
				throw new InvalidZipCode(ZipMsg.ZIPE004, ZipMsg.getMessage(ZipMsg.ZIPE004));//: lower range bound needs to be greater than zero");
			}else if(r.length == 2 && (r[1] < 0)){
				
				throw new InvalidZipCode(ZipMsg.ZIPE005, ZipMsg.getMessage(ZipMsg.ZIPE005));//: upper range bound needs to be greater than zero");
			}
			
			//Test if range has lower bound greater than upper bound
			if(r.length == 2 && r[0] > r[1]){
				throw new InvalidZipCodeRange(ZipMsg.ZIPE002, ZipMsg.getMessage(ZipMsg.ZIPE002));//: range has lower bound greater than upper bound");
			}
			
			// Validate if lower and upper bounds in array is a valid 5 digit zipcode
			for(int i = 0; i < r.length; i++ ){
                // test if the integer is a 5 digit zip code
				validZipCode(r[i]);
			}
			
			//return true range is valid
			range = new ZipRange(r);

		}
		
		return range;
	}

	/**
	 * 
	 * @param range
	 */
	private void doMergeUniqueRanges(ZipRange range){
		//Check to see it binary tree is empty
		if (bTree.isEmpty()){
			
			//lse merge zip code range to binary tree			
			bTree.insert(range.getLowerBound(), range);
			return;
		}
		
		ZipRange zII = bTree.get(range.getLowerBound());
		
		if(zII != null && zII.getUpperBound() == range.getUpperBound()){
			
			return;
			
		}else{
			//Else merge zip code range to binary tree			
			if(!bTree.mergeRange(range)){
                     
				bTree.insert(range.getLowerBound(), range);
						
			}
		}
	
	}
	
}
