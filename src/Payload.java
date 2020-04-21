
public class Payload {
	byte[] payload;
	String ip;
	int port;
	int numFilesToShare;
	int numKb;
	
	public Payload(String ip, int port, int numFilesToShare, int numKb) {
		this.ip = ip;
		this.port = port;
		this.numFilesToShare = numFilesToShare;
		this.numKb = numKb;
	}
	
	public byte[] createPayLoad(String operation) {
		switch(operation) {
			case "pong":
				payload = pongPayLoad();
			case "push":
			case "query":
			case "queryhit":
		}
		
		return payload;
	}
	
	private byte[] pongPayLoad() {
		payload = new byte[16];
		
		// port
		byte[] b = Utilities.intToBigEndianByteArray(port);
		System.arraycopy(b, 0, payload, 0, b.length);
		
		// ip address
		payload[4] = 127;
		payload[5] = 0;
		payload[6] = 0;
		payload[7] = 1;
		
		// num of files to share
		b = Utilities.intToBigEndianByteArray(numFilesToShare);
		System.arraycopy(b, 0, payload, 8, b.length);
		
		// num kilobytes shared
		b = Utilities.intToBigEndianByteArray(numKb);
		System.arraycopy(b, 0, payload, 12, b.length);
		
		return payload;
	}
}
