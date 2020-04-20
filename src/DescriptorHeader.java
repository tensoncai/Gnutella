
public class DescriptorHeader extends Utilities {
	byte[] header;
	byte[] messageId;
	int ttl;
	
	public DescriptorHeader() {
		messageId = new byte[15];
	}
	
	// all headers are 28 bytes
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
		
//		for (int i = 0; i < messageId.length; i++) {
//			System.out.println(messageId[i]);
//		}
		
		
		System.arraycopy(messageId, 0, header, 0, messageId.length);

		// payload descriptor
		header[15] = '1';
		
		// time to live (ttl)
		byte[] ttl = Utilities.intToBigEndianByteArray(1);
		
		for (int i = 0; i < ttl.length; i++) {
			System.out.println("ttl = " + ttl[i]);
		}
		
		System.arraycopy(ttl, 0, header, 16, ttl.length);
		
		// hops = 0
		
		// payload length = 0
		
		for (int i = 0; i < header.length; i++) {
			System.out.println(header[i]);
		}
		
		return header;
	}
	
	private void generateMessageId() {
		for (int i = 0; i < messageId.length; i++) {
			messageId[i] = '2';
		}
	}
}
