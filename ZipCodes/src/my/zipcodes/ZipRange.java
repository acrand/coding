package my.zipcodes;
/**
 * Zip Code Range model class for Shipping zip codes
 * @author acrand
 *
 */
public class ZipRange implements Comparable<ZipRange> {

	protected int upperBound;
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
	 * Get ranges lower bound
	 * @return
	 */
	public int getLowerBound() {
		return lowerBound;
	}
	
	/**
	 * Get range upperbound
	 * @return
	 */
	public int getUpperBound() {
		return upperBound;
	}
	
	/**
	 * Get Range
	 * @return
	 */
	public int[] getRange(){
		return new int[]{this.lowerBound, this.upperBound};
	}

	/**
	 * Is the bound contained in range
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
	    return this.lowerBound +" - " + this.upperBound;
	}
	
}
