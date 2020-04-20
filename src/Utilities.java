
public class Utilities {
	
	// converts an integer into a big endian byte array
	// integer 1 -> byte array [0, 0, 0, 1]
	public static byte[] intToBigEndianByteArray(int value) {
		
		byte[] bigEndianByteArray = {(byte)(value >>> 24),
	            	   						(byte)(value >>> 16),
	            	   						(byte)(value >>> 8),
	            	   						(byte)value};
		
		return bigEndianByteArray;
	}
	
	// converts big endian byte array to integer
	// big endian 1 = [0, 0, 0, 1]
	// little endian 1 = [1, 0, 0, 0]
	public static int convertBigEndianByteArrayToInt(byte[] b) {  
		
		int value = ((b[0] & 0xFF) << 24) | 
					((b[1] & 0xFF) << 16) | 
					((b[2] & 0xFF) << 8)  | 
					(b[3] & 0xFF);
	
	    return value;
	}
}
