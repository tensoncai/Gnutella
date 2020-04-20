
public class Utilities {
	
	// converts an integer into a big endian byte array
	public static byte[] intToBigEndianByteArray(int value) {
		
		byte[] littleEndianByteArray = {(byte)(value >>> 24),
	            	   						(byte)(value >>> 16),
	            	   						(byte)(value >>> 8),
	            	   						(byte)value};
		
		// reverse the little endian byte array into big endian byte array
		byte temp = littleEndianByteArray[0];
		littleEndianByteArray[0] = littleEndianByteArray[3];
		littleEndianByteArray[3] = temp;
		
		temp = littleEndianByteArray[1];
		littleEndianByteArray[1] = littleEndianByteArray[2];
		littleEndianByteArray[2] = temp;
		byte[] bigEndianByteArray = littleEndianByteArray;
		
	    return bigEndianByteArray;
	}
	
	public int convertByteArrayToInt(byte[] b) {    
	    int MASK = 0xFF;
	    int result = 0;   
	        result = b[0] & MASK;
	        result = result + ((b[1] & MASK) << 8);
	        result = result + ((b[2] & MASK) << 16);
	        result = result + ((b[3] & MASK) << 24);            
	    return result;
	}
}
