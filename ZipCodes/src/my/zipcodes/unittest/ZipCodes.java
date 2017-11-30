package my.zipcodes.unittest;
import java.util.ArrayList;

import my.zipcodes.exceptions.InvalidZipCode;
import my.zipcodes.exceptions.InvalidZipCodeRange;
import my.zipcodes.utils.ZipCodeUtil;


public class ZipCodes {

	static int[] z0 = {10000,40000}, z1 = {95122 , 95131}, z2 = {95000 , 95131}, z3 = {95133};
	//Main JHva class to test Zip Util
	public static void main(String[] args) throws InvalidZipCodeRange,InvalidZipCode {
	
		System.out.println("Input ZipCode Ranges: ");
		ZipCodeUtil.printZipRange(z0, z1,z2,z3);
		
		ArrayList<int[]> mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(z0, z1, z2, z3);
		
		System.out.println("Output ZipCode Range: ");
		
		for(int[] range : mergedZips){
			ZipCodeUtil.printZipRange(range);	
		}
		
		System.out.println("Can't ship to: 10001 "+ZipCodeUtil.getInstance().canShipTo(10001));
		
		System.out.println("Can ship to: 95134 "+ZipCodeUtil.getInstance().canShipTo(95134));
		
	}
	
}
