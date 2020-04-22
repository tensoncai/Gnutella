import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Reply extends Thread {
	String ip;
	int myPort;
	int clientPort;
	int numFilesToShare;
	int numKb;
	InetAddress IP;
	String operation;
	
	public Reply(String ip, int myPort, int clientPort, int numFilesToShare, 
				 int numKb, InetAddress IP, String operation) throws UnknownHostException {
		this.ip = ip;
		this.myPort = myPort;
		this.clientPort = clientPort;
		this.numFilesToShare = numFilesToShare;
		this.numKb = numKb;
		this.IP = InetAddress.getByName("localhost");
		this.operation = operation;
	}
	
	public void run() {
		try {
			action(operation);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void action(String operation) throws IOException {
		switch(operation) {
			case "pong":
				pongReply();
				break;
			case "push":
			case "query":
			case "queryhit":
		}
		
	}
	
	public byte[] pongReply() throws IOException {
		DescriptorHeader header = new DescriptorHeader();
		byte[] head = header.createHeader("pong");
		
		Payload payload = new Payload(ip, myPort, numFilesToShare, numKb);
		byte[] pay = payload.createPayLoad("pong");
		
		byte[] pong = new byte[head.length + pay.length];
		System.arraycopy(head, 0, pong, 0, head.length);
		System.arraycopy(pay, 0, pong, head.length, pay.length);
		
		sendReply(pong);
		
		return pong;
	}
	
	public void sendReply(byte[] message) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(message, message.length, IP, clientPort);
		socket.send(packet);
		socket.close();
	}
}
