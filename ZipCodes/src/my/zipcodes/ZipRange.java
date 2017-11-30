package my.zipcodes;
/**
 * Zip Code Range model class for Shipping zip codes
 * @author acrand
 *
 */
public class ZipRange implements Comparable<ZipRange> {

	/*
	 * zip code range upper bound
	 */
	protected int upperBound;
	
	/*
	 * zip code range lower bound
	 */
	protected int lowerBound;
	
	/**
	 * Constructor for zip code range
	 * @param range
	 */
	public ZipRange(int[] range){
		// test if range > 2 
		assert range.length > 2;
		
		if(range.length == 2){
			this.upperBound = range[1];
			this.lowerBound = range[0];
			
		}else{
			this.lowerBound = this.upperBound = range[0];
		}
		
	}
	/**
	 * 
	 * @return
	 */
	public boolean isRange(){
		if(lowerBound == upperBound){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLowerBound() {
		return lowerBound;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getUpperBound() {
		return upperBound;
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] getRange(){
// @TODO: this is how I would have implmented but commented out to be more accurate with problem		
//		
//		if(this.lowerBound == this.upperBound){
//			return new int[]{this.lowerBound};
//		}
		return new int[]{this.lowerBound, this.upperBound};
	}

	/**
	 * 
	 * @param bound
	 * @return
	 */
	public boolean containsBound(int bound){
		if(bound >= this.lowerBound && bound <= this.upperBound ){
			return true;
		}
		
		return false;
	}
	
	@Override
	public int compareTo(ZipRange other) {

		if (this.lowerBound < other.lowerBound || this.upperBound < other.upperBound){
			
			return -1;
			
		}else if (this.lowerBound > other.lowerBound  || this.upperBound > other.upperBound){
			
			return  1;	
		}
	    	
		return 0;	
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lowerBound;
		result = prime * result + upperBound;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZipRange other = (ZipRange) obj;
		if (lowerBound != other.lowerBound)
			return false;
		if (upperBound != other.upperBound)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
// @TODO: this is how I would have implmented but commented out to be more accurate with problem		
//		if(this.lowerBound == this.upperBound){
//		  return String.valueOf(this.lowerBound);	
//		}
	    return this.lowerBound +" - " + this.upperBound;
	}
	
}
