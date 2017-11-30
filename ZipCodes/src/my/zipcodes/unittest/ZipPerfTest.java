package my.zipcodes.unittest;

import java.util.ArrayList;
import java.util.Random;

import my.zipcodes.exceptions.InvalidZipCode;
import my.zipcodes.utils.ZipCodeUtil;

import org.junit.Assert;
import org.junit.Test;

public class ZipPerfTest{

	ArrayList<int[]> mergedZips = new ArrayList<int[]>();
	
	public int[] genZipCodeRange(int i) {
	    Random r = new Random( System.currentTimeMillis() + i);
	    
	    int zipCode = 10000 + r.nextInt(20000);
	    int zipCode2 = 100 + zipCode;
	    
	    return new int[]{zipCode, zipCode2};
	}

	
	@Test(timeout = 100)
	public void testPerf() {
		int zipCodesSize = 100;
		//interger arrays for happy path
		System.out.println("********************************** Start testHappyPath() **********************************");
		
		
		try{
			int[][] genZips = new int[zipCodesSize][1];
		    for(int i = 0; i < zipCodesSize; i++){
		    	genZips[i] = genZipCodeRange(i);
		    }
		    ZipCodeUtil.printZipRange(genZips);
		    
			System.out.println("Generated zip code range length: "+genZips.length);
	
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(genZips);
			
			System.out.println("Merged size: "+mergedZips.size());
			
			System.out.println("<----------------------- :Output ");
			
			for(int[] range : mergedZips){
				ZipCodeUtil.printZipRange(range);	
			}
			
			System.out.println("<----------------------- :ZipCode Range");

			System.out.println();
			
			System.out.println("----------------- Test ZipCodes -------------------");

						
			System.out.println("----------------- End Test ZipCodes -------------------");

		
		} catch(InvalidZipCode e){
			System.err.println(e.getErrorCode() + " - " +  e.getMessage());
			Assert.fail(e.getErrorCode() + " - " + e.getMessage());
		}finally{

			System.out.println("********************************** End testHappyPath() **********************************");
			System.out.println();
			System.out.println();
		}

	}
}
