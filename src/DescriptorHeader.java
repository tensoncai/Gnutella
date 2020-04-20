
public class DescriptorHeader extends Utilities {
	byte[] header;
	char[] messageId;
	int ttl;
	
	public DescriptorHeader() {
		messageId = new char[15];
	}
	
	public byte[] createHeader(String operation) {
		switch(operation) {
			case "ping": 
				header = createPingHeader();
				break;
			case "pong":
			case "push":
			case "query":
			case "queryhit":
		}
		
		return header;
	}
	
	private byte[] createPingHeader() {
		header = new byte[28];
		
		// message id
		generateMessageId();
		System.arraycopy(messageId, 0, header, 0, messageId.length);
		
		// payload descriptor
		header[15] = '1';
		
		// time to live (ttl)
		byte[] ttl = Utilities.intToBigEndianByteArray(1);
		System.arraycopy(ttl, 0, header, 16, ttl.length);
		
		// hops = 0
		
		// payload length = 0
		
		return header;
	}
	
	private void generateMessageId() {
		for (int i = 0; i < messageId.length; i++) {
			messageId[i] = '1';
		}
	}
}
