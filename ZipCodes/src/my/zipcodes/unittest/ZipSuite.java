package my.zipcodes.unittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)				
@Suite.SuiteClasses({				
  ZipTest.class,
  ZipPerfTest.class,  			
})	
public class ZipSuite {

}
