package my.zipcodes.unittest;

import java.util.ArrayList;

import my.zipcodes.exceptions.InvalidZipCode;
import my.zipcodes.messages.ZipMsg;
import my.zipcodes.utils.ZipCodeUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Validate that the Zip Code solutions is functional, includes are the following test:
 * 
 * my.zipcodes.unittest.ZipTest.testHappyPath() - test no merge happy path case
 * my.zipcodes.unittest.ZipTest.testMergeZips() - test merge use case
 * my.zipcodes.unittest.ZipTest.testErrorPath() - test error for zip codes and ranges
 * my.zipcodes.unittest.ZipTest.devUnitTest()  - addhoc test for unit test
 * 
 * 
 * @author acrand
 *
 */
public class ZipTest {
	// - Range
	// - is valid range
	// - 5 digits,  lowest to highest, 2 items in array,
	//
	//   invalid - more than 5 digits, less tan 5 digits,  highest to lowest, negative number, more than 2 items in array,
		
	//static ZipCodeUtil zipUtil;
	ArrayList<int[]> mergedZips, expected;
	
	@BeforeClass
	public static void  init(){
		//zipUtil = ZipCodeUtil.getInstance();
	}
	
	@Before
	public void setup(){
		expected = new ArrayList<int[]>();
		mergedZips = new ArrayList<int[]>();
	}
	
	@After
	public void cleanUp(){
		ZipCodeUtil.getInstance().reset();
	}
	
	@Test
	public void testHappyPath() {
		
		//Set expected merged zip code range Collection 
		expected.add(new int[]{94133,94133});
		expected.add(new int[]{94200,94299});
		expected.add(new int[]{94600,94699});
		
		//interger arrays for happy path
		int[] a1 = new int[] {94133,94133}, a2 = {94200,94299}, a3 = {94600,94699};

		int canShip1 =   94199, canShip2 = 94300, canShip3 = 65532;
		
		//
		int cantShip1 =  94133, cantShip2 = 94650, cantShip3 = 94230, cantShip4  = 94600, cantShip5 = 94299;
		
		System.out.println("********************************** Start testHappyPath() **********************************");
		
		System.out.print("Input ZipCode Ranges:---------------> ");
		ZipCodeUtil.printZipRange(a1, a2, a3);
		
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(a1, a2, a3);
			
			System.out.println("<----------------------- :Output ");
			
			for(int[] range : mergedZips){
				ZipCodeUtil.printZipRange(range);	
			}
			
			Assert.assertArrayEquals(expected.toArray(), mergedZips.toArray());

			System.out.println("<----------------------- :ZipCode Range");

			System.out.println();
			
			System.out.println("----------------- Test ZipCodes -------------------");

			Assert.assertTrue("Can ship to: "+ canShip1, ZipCodeUtil.getInstance().canShipTo(canShip1));
			System.out.println("Can ship to: "+ canShip1 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip1));
			
			Assert.assertTrue("Can ship to: "+ canShip2, ZipCodeUtil.getInstance().canShipTo(canShip2));
			System.out.println("Can ship to: "+ canShip2 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip2));
			
			Assert.assertTrue("Can ship to: "+ canShip3, ZipCodeUtil.getInstance().canShipTo(canShip3));
			System.out.println("Can ship to: "+ canShip3 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip3));
			
			Assert.assertFalse("Can't ship to: "+ cantShip1, ZipCodeUtil.getInstance().canShipTo(cantShip1));
			System.out.println("Can't ship to: "+ cantShip1 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip1));
			
			Assert.assertFalse("Can't ship to: "+ cantShip2, ZipCodeUtil.getInstance().canShipTo(cantShip2));
			System.out.println("Can't ship to: "+ cantShip2 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip2));
			
			Assert.assertFalse("Can't ship to: "+ cantShip3, ZipCodeUtil.getInstance().canShipTo(cantShip3));
			System.out.println("Can't ship to: "+ cantShip3 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip3));
			
			Assert.assertFalse("Can't ship to: "+ cantShip4, ZipCodeUtil.getInstance().canShipTo(cantShip4));
			System.out.println("Can't ship to: "+ cantShip4 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip4));
			
			Assert.assertFalse("Can't ship to: "+ cantShip5 , ZipCodeUtil.getInstance().canShipTo(cantShip5));
			System.out.println("Can't ship to: "+ cantShip5 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip5));
			
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

	@Test
	public void testMergeZips() {
	
		//Set expected merged zip code range Collection 
		expected.add(new int[]{94133,94133});
		expected.add(new int[]{94200,94699});
		
		//interger arrays for happy path
		int[] a1 = new int[] {94133,94133}, a2 = {94200,94299}, a3 = {94226,94699};
		
		int canShip1 =   94199, canShip2 = 94134, canShip3 = 65532;
		
		//
		int cantShip1 =  94133, cantShip2 = 94650, cantShip3 = 94230, cantShip4  = 94600, cantShip5 = 94299,
				cantShip6 = 94300;
		
		System.out.println("********************************** Start testMergeZips() **********************************");
		
		System.out.print("Input ZipCode Ranges:---------------> ");
		ZipCodeUtil.printZipRange(a1, a2, a3);
		
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(a1, a2, a3);
			
			System.out.println("<----------------------- :Output ");
			
			for(int[] range : mergedZips){
				ZipCodeUtil.printZipRange(range);	
			}
			
			Assert.assertArrayEquals(expected.toArray(), mergedZips.toArray());
			
			System.out.println("<----------------------- :ZipCode Range");

			System.out.println();

			System.out.println("----------------- Test ZipCodes -------------------");

			Assert.assertTrue("Can ship to: "+ canShip1, ZipCodeUtil.getInstance().canShipTo(canShip1));
			System.out.println("Can ship to: "+ canShip1 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip1));

			Assert.assertTrue("Can ship to: "+ canShip2, ZipCodeUtil.getInstance().canShipTo(canShip2));
			System.out.println("Can ship to: "+ canShip2 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip2));

			Assert.assertTrue("Can ship to: "+ canShip3 , ZipCodeUtil.getInstance().canShipTo(canShip3));
			System.out.println("Can ship to: "+ canShip3 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip3));
			
			Assert.assertFalse("Can't ship to: "+ cantShip1, ZipCodeUtil.getInstance().canShipTo(cantShip1));
			System.out.println("Can't ship to: "+ cantShip1 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip1));
			
			Assert.assertFalse("Can't ship to: "+ cantShip2, ZipCodeUtil.getInstance().canShipTo(cantShip2));
			System.out.println("Can't ship to: "+ cantShip2 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip2));
			
			Assert.assertFalse("Can't ship to: "+ cantShip3 , ZipCodeUtil.getInstance().canShipTo(cantShip3));
			System.out.println("Can't ship to: "+ cantShip3 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip3));
			
			Assert.assertFalse("Can't ship to: "+ cantShip4,ZipCodeUtil.getInstance().canShipTo(cantShip4));
			System.out.println("Can't ship to: "+ cantShip4 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip4));
			
			Assert.assertFalse("Can't ship to: "+ cantShip5 ,ZipCodeUtil.getInstance().canShipTo(cantShip5));
			System.out.println("Can't ship to: "+ cantShip5 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip5));

			Assert.assertFalse("Can't ship to: "+ cantShip6 ,ZipCodeUtil.getInstance().canShipTo(cantShip6));
			System.out.println("Can't ship to: "+ cantShip6 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip6));

			System.out.println("----------------- End Test ZipCodes -------------------");

		
		} catch(InvalidZipCode e){

			System.err.println(e.getErrorCode() + " - " +  e.getMessage());
			Assert.fail(e.getErrorCode() + " - " + e.getMessage());
		}finally{

			System.out.println("********************************** End testMergeZips() **********************************");
			System.out.println();
			System.out.println();
		}
	}
	
	@Test
	public void testErrorPath() {
		//interger arrays for error paths
		int[] e1 = new int[] {4133}, // error too not 5 digits  ZIPE003
			  e2 = {942004299}, // error greater than 5 digits ZIPE003
			  e3 = {-94600}, // error zip negative number ZIPE004
			  e4 = {50000, 40000}, // lower bound larger than upper bound ZIPE002
			  e5 = {30000, 40000, 50000}, // too many zipCodes  ZIPE001
			  e6 = {}, //Empty ZIPE006
		      e7 = {99999, -99999},//ZIPE005 = Upper range bound needs to be greater than zero.
		      e8 = {99999},//ZIPE000 = Must provide more than one range to compare.
	          e9 = {00000};//ZIPE007 = Zip Code greater than 0
	  	
		System.out.println("********************************** Start testErrorPath() **********************************");
		
		System.out.print("Input ZipCode Ranges:---------------> ");
		ZipCodeUtil.printZipRange(e1, e2, e3, e4, e5,e6, e7,e8, e9);
		
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e1);
		
		} catch(InvalidZipCode e){
			Assert.assertEquals(ZipMsg.ZIPE003, e.getErrorCode());
		}

		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e2);
		
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE003, e.getErrorCode());
		}

		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e3);
		
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE004, e.getErrorCode());
		}
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e4);
		
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE002, e.getErrorCode());
		}
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e5);
		
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE001, e.getErrorCode());
		}
		
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e6);
		
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE006, e.getErrorCode());
		}
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e7);
		
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE005, e.getErrorCode());
		}
		
		try{
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(e8);
		
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE000, e.getErrorCode());
		}

		try{
			ZipCodeUtil.getInstance().canShipTo(e9[0]);
			
		} catch(InvalidZipCode e){
			
			Assert.assertEquals(ZipMsg.ZIPE007, e.getErrorCode());
		}
		finally{

			System.out.println("********************************** End testErrorPath() **********************************");
			System.out.println();
			System.out.println();
		}
		
	}

	@Test
	public void devUnitTest(){
		//Zip Ranges
		int[] z0 = {10001,94999}, z1 = {95122 , 95131}, z2 = {95000 , 95131}, z3 = {95133};
		
		//can ship coed
		int canShip1 =   95132, canShip2 = 10000, canShip3 = 99999;
		
		//can't ship coed
		int cantShip1 =  94999, cantShip2 = 95000, cantShip3 = 95133;

		System.out.println("********************************** Start devUnitTest() **********************************");
		
		try{
			System.out.print("Input ZipCode Ranges:---------------> ");
			ZipCodeUtil.printZipRange(z0, z1,z2,z3);
			
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(z0, z1, z2, z3);
			
			System.out.println("<----------------------- :Output ");
			
			for(int[] range : mergedZips){
				ZipCodeUtil.printZipRange(range);	
			}
			
			System.out.println("<----------------------- :ZipCode Range");

			System.out.println();

			System.out.println("----------------- Test ZipCodes -------------------");

			Assert.assertTrue("Can ship to: "+ canShip1, ZipCodeUtil.getInstance().canShipTo(canShip1));
			System.out.println("Can ship to: "+ canShip1 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip1));
			
			Assert.assertTrue("Can ship to: "+ canShip2, ZipCodeUtil.getInstance().canShipTo(canShip2));
			System.out.println("Can ship to: "+ canShip2 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip2));
			
			Assert.assertTrue("Can ship to: "+ canShip3, ZipCodeUtil.getInstance().canShipTo(canShip3));
			System.out.println("Can ship to: "+ canShip3 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip3));
			
			Assert.assertFalse("Can't ship to: "+ cantShip1,ZipCodeUtil.getInstance().canShipTo(cantShip1));
			System.out.println("Can't ship to: "+ cantShip1 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip1));
			
			Assert.assertFalse("Can't ship to: "+ cantShip2 ,ZipCodeUtil.getInstance().canShipTo(cantShip2));
			System.out.println("Can't ship to: "+ cantShip2 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip2));
			
			Assert.assertFalse("Can't ship to: "+ cantShip3 ,ZipCodeUtil.getInstance().canShipTo(cantShip3));
			System.out.println("Can't ship to: "+ cantShip3 +" "+ ZipCodeUtil.getInstance().canShipTo(cantShip3));
			
			System.out.println("----------------- End Test ZipCodes -------------------");

			//additional test for adding a new restricted zp code to Zip Utility instance
			mergedZips = ZipCodeUtil.getInstance().mergeUniqueRanges(new int[]{canShip1});

			for(int[] range : mergedZips){
				ZipCodeUtil.printZipRange(range);	
			}
			
			Assert.assertFalse("New Can't ship to: "+ canShip1, ZipCodeUtil.getInstance().canShipTo(canShip1));
			System.out.println("New Can't ship to: "+ canShip1 +" "+ ZipCodeUtil.getInstance().canShipTo(canShip1));
			
		} catch(InvalidZipCode e){
			System.err.println(e.getErrorCode() + " - " +  e.getMessage());
			Assert.fail(e.getErrorCode() + " - " + e.getMessage());
		}finally{	

			System.out.println("********************************** End devUnitTest() **********************************");
			System.out.println();
			System.out.println();
		}
	}

//	@Test
//	public void fooTest(){
//
//		System.out.println("********************************** Start fooTest() **********************************");
//		
//		System.out.println("********************************** End fooTest() **********************************");
//		System.out.println();
//		System.out.println();
//	}

}
