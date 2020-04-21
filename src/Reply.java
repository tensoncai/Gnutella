
public class Reply extends Thread {
	String ip;
	int port;
	int numFilesToShare;
	int numKb;
	
	public Reply(String ip, int port, int numFilesToShare, int numKb) {
		
	}
	
	public void run() {
		
	}
	
	public byte[] action(String operation) {
		switch(operation) {
			case "pong":
				pongReply();
				break;
			case "push":
			case "query":
			case "queryhit":
		}
		
	}
	
	public byte[] pongReply() {
		DescriptorHeader header = new DescriptorHeader();
		byte[] head = header.createHeader("pong");
		
		Payload payload = new Payload(ip, port, numFilesToShare, numKb);
		byte[] pay = payload.createPayLoad("pong");
		
		byte[] pong = new byte[head.length + pay.length];
		System.arraycopy(head, 0, pong, 0, head.length);
		System.arraycopy(pay, 0, pong, head.length, pay.length);
		
		return pong;
	}
	
	public void sendReply() {
		
	}
}
