import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Listen extends Thread {
	String ip;
	int port;
	int numFilesToShare;
	int numKb;
	InetAddress IP;
	List<Node> neighbors;
	Socket socket = null;
	
	public Listen(String ip, int port, int numFilesToShare, 
				  int numKb, InetAddress IP, List<Node> neighbors) throws UnknownHostException {
		this.ip = ip;
		this.port = port;
		this.numFilesToShare = numFilesToShare;
		this.numKb = numKb;
		this.IP = InetAddress.getByName("localhost");
		this.neighbors = neighbors;
	}
	
	public void run() {
		try {
			listenAtPort();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	private void listenAtPort() throws IOException {
		DatagramSocket socket = new DatagramSocket(port);
		DatagramPacket packet;
		byte[] incoming;
		
		while (true) {
			incoming = new byte[256];
			packet = new DatagramPacket(incoming, incoming.length);
			socket.receive(packet);
			
			byte[] received;
			System.out.println("length received = " + packet.getLength());
			
			// pings are always sent from a port 1 above the listening port (my rules to make this work)
			// that's why subtract 1 to get the port the client is listening at
			int clientPort = packet.getPort() - 1;
			System.out.println("clientPort = " + clientPort);
			
			char descriptor = (char) incoming[15];
			System.out.println("descriptor = " + descriptor);
			
			switch (descriptor) {
				case '1':
					// When a ping is received, send a pong back
					Reply myPong = new Reply(ip, port, clientPort, numFilesToShare, numKb, IP, "pong");
					myPong.start();
					
					// After sending your pong, send a pong for each of your neighbors
					for (Node neighbor : neighbors) {
						Reply pongReply = new Reply(neighbor.ip, neighbor.port, clientPort, 
								neighbor.numFilesToShare, neighbor.numKb, neighbor.IP, "pong");
						pongReply.start();
					}
					
					break;
				case '2':
					received = new byte[packet.getLength()];
					System.arraycopy(incoming, 0, received, 0, received.length);
					
					// When a pong is received, check if you have enough neighbors
					if (neighbors.size() < 3) {
						Node newNeighbor = parsePongMessage(received);
						neighbors.add(newNeighbor);
					}
					
					break;
				case '3':
				case '4':
				case '5':
			}
			
			
			
			// now analyze packet received. Find out which operation pong, query, etc. to do
			// if ping, then make reply object and do pong operation
			
//			System.out.println("listening");
//			ServerSocket serverSocket = new ServerSocket(listeningPort);
//			socket = serverSocket.accept(); 
//			System.out.println("connected");
//			InputStream in = socket.getInputStream();
//		    DataInputStream dis = new DataInputStream(in);
//		    byte[] incoming = new byte[1024];
//		    int bytesRead = dis.read(incoming);
//		    System.out.println("received");
//		    byte[] b = {incoming[24], incoming[25], incoming[26], incoming[27]};
//		    int payloadLen = Utilities.convertBigEndianByteArrayToInt(b);
//		    int totalLength = 28 + payloadLen;
//		    
//		    if (bytesRead != totalLength) {
//		    		System.out.println("Something wrong. bytesRead != totalLength");
//		    }
//		    
		    // give incoming byte array to another thread for processing
		} 
	}
	
	public Node parsePongMessage(byte[] pongMessage) throws UnknownHostException {
		byte[] b = {pongMessage[28], pongMessage[29], pongMessage[30], pongMessage[31]};
		int neighborPort = Utilities.convertBigEndianByteArrayToInt(b);
		
		String neighborIp = "";
		for (int i = 32; i < 36; i++) {
			neighborIp = neighborIp + (char) pongMessage[i];
		}
		
		byte[] num = {pongMessage[36], pongMessage[37], pongMessage[38], pongMessage[39]};
		int numFiles = Utilities.convertBigEndianByteArrayToInt(num);
		
		byte[] kb = {pongMessage[40], pongMessage[41], pongMessage[42], pongMessage[43]};
		int numKB = Utilities.convertBigEndianByteArrayToInt(kb);
		
		Node neighbor = new Node(neighborIp, neighborPort, numFiles, numKB);
				
		return neighbor;
	}
}
